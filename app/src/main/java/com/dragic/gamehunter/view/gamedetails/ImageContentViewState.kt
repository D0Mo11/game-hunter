package com.dragic.gamehunter.view.gamedetails

import com.dragic.gamehunter.model.GameDetailsEntity
import com.dragic.gamehunter.utils.getDateString
import com.dragic.gamehunter.utils.priceToUsd

data class ImageContentViewState(
    val id: Int,
    val gameTitle: String,
    val thumbnail: String,
    val lowestPrice: String,
    val dateLowestPrice: String,
    val isFavorite: Boolean,
)

fun GameDetailsEntity.toImageContentViewState() = ImageContentViewState(
    id = id,
    gameTitle = info.title,
    thumbnail = info.thumbnail,
    lowestPrice = priceToUsd(cheapestPrice.price),
    dateLowestPrice = getDateString(cheapestPrice.date),
    isFavorite = isFavorite,
)
