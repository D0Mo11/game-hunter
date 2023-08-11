package com.dragic.gamehunter.view.home

import com.dragic.gamehunter.model.DealEntity
import com.dragic.gamehunter.utils.priceToUsd
import com.dragic.gamehunter.utils.savingsToPercentage

data class DealViewState(
    val id: Int,
    val gameTitle: String,
    val salePrice: String,
    val normalPrice: String,
    val savePercentage: String,
    val steamRating: String,
    val dealRating: String,
    val thumbnail: String,
)

fun DealEntity.toDealViewState() = DealViewState(
    id = id.toInt(),
    gameTitle = gameTitle,
    salePrice = priceToUsd(salePrice),
    normalPrice = priceToUsd(normalPrice),
    savePercentage = savingsToPercentage(savings),
    steamRating = steamRating.toString(),
    dealRating = dealRating.toString(),
    thumbnail = thumbnail,
)
