package com.dragic.gamehunter.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

private const val BASE_URL = "https://www.cheapshark.com/api/1.0"
private const val DEAL_RATING = "Deal Rating"
private const val SAVINGS = "Savings"
private const val REVIEWS = "Reviews"

class CheapSharkApi @Inject constructor(private val client: HttpClient) {

    suspend fun getAllDeals(pageNumber: Int): List<DealResponse> = client.get("$BASE_URL/deals?pageNumber=$pageNumber").body()

    suspend fun getGameDetails(gameId: Int): GameDetailsResponse = client.get("$BASE_URL/games?id=$gameId").body()

    suspend fun getStoreInfo(): List<StoreInfoResponse> = client.get("$BASE_URL/stores").body()

    suspend fun getAllDealsByDealRating(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$DEAL_RATING").body()

    suspend fun getAllDealsBySavings(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$SAVINGS").body()

    suspend fun getAllDealsByReviews(): List<DealResponse> = client.get("$BASE_URL/deals?sortBy=$REVIEWS").body()
}
