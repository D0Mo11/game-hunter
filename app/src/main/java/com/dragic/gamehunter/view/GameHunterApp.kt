package com.dragic.gamehunter.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dragic.gamehunter.view.navigation.Details
import com.dragic.gamehunter.view.navigation.Home
import com.dragic.gamehunter.view.navigation.Navigation
import com.dragic.gamehunter.view.uicomponents.BottomBar

private const val TWEEN_VISIBILITY_ANIMATION_DURATION = 500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameHunterApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Home.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute != Details.routeWithArgs,
                enter = slideInVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeIn(),
                exit = slideOutVertically(
                    animationSpec = tween(TWEEN_VISIBILITY_ANIMATION_DURATION, easing = LinearEasing)
                ) + fadeOut(),
            ) {
                NavigationBar(Modifier.background(MaterialTheme.colorScheme.background)) {
                    BottomBar(
                        route = currentRoute,
                        onRouteSelected = { targetRoute ->
                            navController.navigate(targetRoute) {
                                restoreState = true
                                popUpTo(currentRoute) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

            }
        }
    ) { innerPadding ->
        Navigation(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
