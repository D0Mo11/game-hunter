package com.dragic.gamehunter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dragic.gamehunter.settings.UserSettings
import com.dragic.gamehunter.view.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val userSettings: UserSettings
) : ViewModel() {

    var appTheme by mutableStateOf(userSettings.theme)

    fun changeTheme(theme: AppTheme) {
        appTheme = if (theme == AppTheme.LIGHT) {
            AppTheme.DARK
        } else {
            AppTheme.LIGHT
        }
        userSettings.theme = appTheme
    }
}
