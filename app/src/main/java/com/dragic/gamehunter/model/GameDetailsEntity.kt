package com.dragic.gamehunter.model

data class GameDetailsEntity(
    val id: Int,
    val info: GameInfo,
    val cheapestPrice: GameCheapestPrice,
    val deals: List<GameDetailsDeal>,
    val isFavorite: Boolean,
)

data class GameInfo(
    val title: String,
    val thumbnail: String,
)

data class GameCheapestPrice(
    val price: Double,
    val date: Int,
)

data class GameDetailsDeal(
    val storeId: String,
    val storeName: String,
    val storeLogoUrl: String,
    val dealId: String,
    val salePrice: Double,
    val normalPrice: Double,
    val savings: Double,
)
