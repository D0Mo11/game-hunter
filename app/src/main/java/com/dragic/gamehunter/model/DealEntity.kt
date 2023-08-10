package com.dragic.gamehunter.model

data class DealEntity(
    val id: String,
    val gameTitle: String,
    val salePrice: Double,
    val normalPrice: Double,
    val savings: Double,
    val steamRating: Double,
    val dealRating: Double,
    val thumbnail: String,
)
