package com.dragic.gamehunter.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.dragic.gamehunter.view.theme.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class UserSettings @Inject constructor(
    @ApplicationContext context: Context
) {

    private val themeStream: MutableStateFlow<AppTheme>
    var theme: AppTheme
        get() = AppTheme.fromOrdinal(preferences.getInt("app_theme", AppTheme.LIGHT.ordinal))
        set(value) {
            themeStream.value = value
            preferences.edit {
                putInt("app_theme", value.ordinal)
            }
        }

    private val preferences: SharedPreferences =
        context.getSharedPreferences("sample_theme", Context.MODE_PRIVATE)

    init {
        themeStream = MutableStateFlow(theme)
    }
}
