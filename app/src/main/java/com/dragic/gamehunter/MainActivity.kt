package com.dragic.gamehunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dragic.gamehunter.view.GameHunterApp
import com.dragic.gamehunter.view.theme.GameHunterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameHunterTheme {
                GameHunterApp()
            }
        }
    }
}
