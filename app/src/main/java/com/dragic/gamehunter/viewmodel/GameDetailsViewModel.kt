package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.di.GameId
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.view.gamedetails.DealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.ImageContentViewState
import com.dragic.gamehunter.view.gamedetails.toDealDetailsViewState
import com.dragic.gamehunter.view.gamedetails.toImageContentViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: DealRepository,
    @GameId private val gameId: Int,
) : ViewModel() {
    var gameData by mutableStateOf<ImageContentViewState?>(null)
        private set
    var dealData by mutableStateOf<List<DealDetailsViewState>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            repository.fetchGameDetailsData(gameId)
        }
        viewModelScope.launch {
            repository.gameDetailsData().collect { gameDetails ->
                gameData = gameDetails.toImageContentViewState()
            }
        }
        viewModelScope.launch {
            repository.getGameDealsDetails().collect { dealDetails ->
                dealData = dealDetails.map { it.toDealDetailsViewState() }
            }
        }
    }

    fun refreshFavoriteMovie() {
        viewModelScope.launch {
            val game = gameData
            if (game != null) {
                if (game.isFavorite) repository.removeGameById(gameId.toLong()) else repository.insertGame(
                    game.gameTitle,
                    game.thumbnail,
                    gameId.toLong(),
                )
            }
        }
    }
}
