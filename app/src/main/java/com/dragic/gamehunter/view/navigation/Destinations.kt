package com.dragic.gamehunter.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val icon: ImageVector
    val route: String
    val text: String
}

object Home : Destinations {
    override val icon: ImageVector = Icons.Filled.Home
    override val route: String = "home"
    override val text: String = "Home"
}

object Favorites : Destinations {
    override val icon: ImageVector = Icons.Filled.Favorite
    override val route: String = "favorites"
    override val text: String = "Favorites"
}

object Details : Destinations {
    override val icon: ImageVector = Icons.Filled.Info
    override val route: String = "details"
    override val text: String = "Details"
    private const val detailsTypeArg = "deal_id"
    val routeWithArgs = "$route/{$detailsTypeArg}"
    val arguments = listOf(
        navArgument(detailsTypeArg) { type = NavType.IntType }
    )
}

val bottomBarScreens = listOf(Home, Favorites)
