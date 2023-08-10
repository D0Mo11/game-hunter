package com.dragic.gamehunter.view.home

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.R)
fun DealEntity.toDealViewState() = DealViewState(
    id = id.toInt(),
    gameTitle = gameTitle,
    salePrice = priceToUsd(salePrice),
    normalPrice = priceToUsd(normalPrice),
    savePercentage = "-${savingsToPercentage(savings)} OFF",
    steamRating = steamRating.toString(),
    dealRating = dealRating.toString(),
    thumbnail = thumbnail,
)
