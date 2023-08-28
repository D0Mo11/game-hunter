package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {

    var isLoading by mutableStateOf(true)

    init {
        viewModelScope.launch {
            delay(2000)
            isLoading = false
        }
    }
}
