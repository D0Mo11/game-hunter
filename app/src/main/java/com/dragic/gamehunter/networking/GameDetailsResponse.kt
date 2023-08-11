package com.dragic.gamehunter.networking

import com.dragic.gamehunter.model.GameCheapestPrice
import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.model.GameInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val DEAL_REDIRECT_LINK_PREFIX = "https://www.cheapshark.com/redirect?dealID="

@Serializable
data class GameDetailsResponse(
    @SerialName("info")
    val info: GameInfoResponse,
    @SerialName("cheapestPriceEver")
    val cheapestPrice: GameCheapestPriceResponse,
    @SerialName("deals")
    val deals: List<DealDetailsResponse>,
)

@Serializable
data class GameInfoResponse(
    @SerialName("title")
    val title: String,
    @SerialName("thumb")
    val thumbnail: String,
)

@Serializable
data class GameCheapestPriceResponse(
    @SerialName("price")
    val price: String,
    @SerialName("date")
    val date: Int,
)

@Serializable
data class DealDetailsResponse(
    @SerialName("storeID")
    val storeId: String,
    @SerialName("dealID")
    val dealId: String,
    @SerialName("price")
    val salePrice: String,
    @SerialName("retailPrice")
    val normalPrice: String,
    @SerialName("savings")
    val savings: String,
)

fun GameDetailsResponse.toGameDetailsEntity(id: Int) = GameDetailsEntity(
    id = id,
    info = GameInfo(info.title, info.thumbnail),
    cheapestPrice = GameCheapestPrice(cheapestPrice.price.toDouble(), cheapestPrice.date),
    deals = deals.map { it.toGameDetailsDeal() },
    isFavorite = false,
)

fun DealDetailsResponse.toGameDetailsDeal() = GameDetailsDeal(
    storeId = storeId,
    storeName = "",
    storeLogoUrl = "",
    dealId = "$DEAL_REDIRECT_LINK_PREFIX$dealId",
    salePrice = salePrice.toDouble(),
    normalPrice = normalPrice.toDouble(),
    savings = savings.toDouble(),
)
