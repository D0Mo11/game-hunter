package com.dragic.gamehunter.networking

import android.os.Build
import androidx.annotation.RequiresApi
import com.dragic.gamehunter.model.DealEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DealResponse(
    @SerialName("gameID")
    val id: String,
    @SerialName("title")
    val gameTitle: String,
    @SerialName("salePrice")
    val salePrice: String,
    @SerialName("normalPrice")
    val normalPrice: String,
    @SerialName("savings")
    val savings: String,
    @SerialName("steamRatingPercent")
    val steamRating: String,
    @SerialName("dealRating")
    val dealRating: String,
    @SerialName("thumb")
    val thumbnail: String,
)

@RequiresApi(Build.VERSION_CODES.R)
fun DealResponse.toDealEntity() = DealEntity(
    id = id,
    gameTitle = gameTitle,
    salePrice = salePrice.toDouble(),
    normalPrice = normalPrice.toDouble(),
    savings = savings.toDouble(),
    steamRating = steamRating.toDouble(),
    dealRating = dealRating.toDouble(),
    thumbnail = thumbnail
)
