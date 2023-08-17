package com.dragic.gamehunter.viewmodel

import android.util.Log
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

    val appTheme = mutableStateOf(userSettings.theme)

    var useDarkColor by mutableStateOf(
        when (appTheme.value) {
            AppTheme.LIGHT -> false
            AppTheme.DARK -> true
        }
    )

    fun changeTheme(theme: AppTheme) {
        Log.d("ChangeTheme:", "BEFORE CHANGE")
        Log.d("ChangeTheme:", "UserSetting ${userSettings.theme}")
        Log.d("ChangeTheme:", "App theme ${appTheme.value}")
        Log.d("ChangeTheme:", "Theme ${theme.name}")
        Log.d("ChangeTheme:", "DarkColors ${useDarkColor}")

        if (theme == AppTheme.LIGHT) {
            appTheme.value = AppTheme.DARK
            useDarkColor = true
        } else {
            appTheme.value = AppTheme.LIGHT
            useDarkColor = false
        }
        userSettings.theme = appTheme.value
        Log.d("ChangeTheme:", "AFTER CHANGE")
        Log.d("ChangeTheme:", "UserSetting ${userSettings.theme}")
        Log.d("ChangeTheme:", "App theme ${appTheme.value}")
        Log.d("ChangeTheme:", "DarkColors ${useDarkColor}")

    }
}
