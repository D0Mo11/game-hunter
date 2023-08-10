package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.view.favorites.FavoriteGameViewState
import com.dragic.gamehunter.view.favorites.toFavoriteGameViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    repository: DealRepository,
) : ViewModel() {

    var favoriteGames by mutableStateOf<List<FavoriteGameViewState>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            repository.getFavoriteGames().collect { games ->
                favoriteGames = games.map { it.toFavoriteGameViewState() }
            }
        }
    }
}
