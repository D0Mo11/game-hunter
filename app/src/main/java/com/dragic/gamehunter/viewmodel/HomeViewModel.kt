package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragic.gamehunter.repository.DealRepository
import com.dragic.gamehunter.view.home.DealViewState
import com.dragic.gamehunter.view.home.toDealViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DealRepository,
) : ViewModel() {
    var dealData by mutableStateOf<List<DealViewState>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            dealData = repository.dealData().map { it.toDealViewState() }
        }
    }

    fun fetchDealsByDealRating() {
        viewModelScope.launch {
            dealData = repository.dealDataByDealRating().map { it.toDealViewState() }
        }
    }

    fun fetchDealsBySavings() {
        viewModelScope.launch {
            dealData = repository.dealDataBySavings().map { it.toDealViewState() }
        }
    }

    fun fetchDealsByReviews() {
        viewModelScope.launch {
            dealData = repository.dealDataByReviews().map { it.toDealViewState() }
        }
    }
}
