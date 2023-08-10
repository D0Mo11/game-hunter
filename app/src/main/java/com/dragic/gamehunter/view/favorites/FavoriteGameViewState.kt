package com.dragic.gamehunter.view.favorites

import gamehunterdb.GameEntity

data class FavoriteGameViewState(
    val dealId: Int,
    val gameTitle: String,
    val thumbnail: String,
)

fun GameEntity.toFavoriteGameViewState() = FavoriteGameViewState(
    dealId = id.toInt(),
    gameTitle = title,
    thumbnail = thumbnail,
)
