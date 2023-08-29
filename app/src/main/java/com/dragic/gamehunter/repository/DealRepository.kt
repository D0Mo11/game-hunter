package com.dragic.gamehunter.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.dragic.gamehunter.GameHunterDatabase
import com.dragic.gamehunter.di.IoDispatcher
import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.model.StoreInfoEntity
import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.networking.toDealEntity
import com.dragic.gamehunter.networking.toGameDetailsEntity
import com.dragic.gamehunter.networking.toStoreInfoEntity
import gamehunterdb.GameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


private const val BASE_STORE_LOGO_URL = "https://www.cheapshark.com"

@Singleton
class DealRepository @Inject constructor(
    private val cheapSharkApi: CheapSharkApi,
    database: GameHunterDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineContext,
) {

    private val queries = database.gameEntityQueries
    private val gameDetails = MutableSharedFlow<GameDetailsEntity>()
    private val storeInfo = MutableSharedFlow<List<StoreInfoEntity>>()

    suspend fun dealData(pageNumber: Int): List<DealEntity> = cheapSharkApi.getAllDeals(pageNumber).map { it.toDealEntity() }

    fun gameDetailsData(): Flow<GameDetailsEntity> =
        combine(
            gameDetails,
            getFavoriteGames(),
        ) { game, favoriteGames ->
            val favGame = favoriteGames.find { game.id == it.id.toInt() }
            game.copy(isFavorite = favGame != null)
        }

    suspend fun fetchGameDetailsData(gameId: Int) {
        gameDetails.emit(cheapSharkApi.getGameDetails(gameId).toGameDetailsEntity(id = gameId))
        storeInfo.emit(cheapSharkApi.getStoreInfo().map { it.toStoreInfoEntity() })
    }

    fun getFavoriteGames(): Flow<List<GameEntity>> = queries.getAllGames().asFlow().mapToList(ioDispatcher)

    fun getGameDealsDetails(): Flow<List<GameDetailsDeal>> =
        combine(
            gameDetails,
            storeInfo
        ) { details, info ->
            val storeIdToStoreInfoMap = info.associateBy { it.storeId }
            details.deals.map {
                it.copy(
                    storeName = storeIdToStoreInfoMap[it.storeId]!!.storeName,
                    storeLogoUrl = "$BASE_STORE_LOGO_URL${storeIdToStoreInfoMap[it.storeId]!!.logoUrl}",
                )
            }
        }

    suspend fun dealDataByDealRating(): List<DealEntity> = cheapSharkApi.getAllDealsByDealRating().map { it.toDealEntity() }
    suspend fun dealDataBySavings(): List<DealEntity> = cheapSharkApi.getAllDealsBySavings().map { it.toDealEntity() }
    suspend fun dealDataByReviews(): List<DealEntity> = cheapSharkApi.getAllDealsByReviews().map { it.toDealEntity() }

    suspend fun removeGameById(gameId: Long) = withContext(ioDispatcher) {
        queries.deleteGameById(gameId)
    }

    suspend fun insertGame(gameTitle: String, thumbnail: String, id: Long?) = withContext(ioDispatcher) {
        queries.insertGame(
            id = id,
            title = gameTitle,
            thumbnail = thumbnail
        )
    }
}
