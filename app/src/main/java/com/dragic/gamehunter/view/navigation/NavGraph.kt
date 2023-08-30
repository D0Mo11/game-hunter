package com.dragic.gamehunter.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dragic.gamehunter.view.favorites.FavoritesScreen
import com.dragic.gamehunter.view.gamedetails.GameDetailsScreen
import com.dragic.gamehunter.view.home.HomeScreen
import com.dragic.gamehunter.view.theme.AppTheme
import com.dragic.gamehunter.viewmodel.FavoritesViewModel
import com.dragic.gamehunter.viewmodel.GameDetailsViewModel
import com.dragic.gamehunter.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@Composable
fun Navigation(
    navController: NavHostController,
    theme: AppTheme,
    onThemeChange: (AppTheme) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                onDealClick = { dealId ->
                    navController.navigateToDetails(dealId)
                },
                dealState = viewModel.dealData,
                onSortByDealRatingClick = { viewModel.fetchDealsByDealRating() },
                onSortByReviewsClick = { viewModel.fetchDealsByReviews() },
                onSortBySavingsClick = { viewModel.fetchDealsBySavings() },
                theme = theme,
                onThemeChange = onThemeChange,
                loadNextPage = { viewModel.loadNextPage() },
            )
        }
        composable(route = Favorites.route) {
            val viewModel: FavoritesViewModel = hiltViewModel()
            FavoritesScreen(
                onGameClick = { gameId ->
                    navController.navigateToDetails(gameId)
                },
                favoriteGamesState = viewModel.favoriteGames,
            )
        }
        composable(
            route = Details.routeWithArgs,
            arguments = Details.arguments
        ) {
            val viewModel: GameDetailsViewModel = hiltViewModel()
            val uriHandler = LocalUriHandler.current
            var isLoading by remember { mutableStateOf(true) }

            LaunchedEffect(key1 = true) {
                delay(2000)
                isLoading = false
            }
            GameDetailsScreen(
                imageContentState = viewModel.gameData,
                dealDetailsState = viewModel.dealData,
                onFavoriteClick = { viewModel.refreshFavoriteMovie() },
                onDealClick = { uriHandler.openUri(it) },
                onBackArrowClick = { navController.navigateUp() },
                isLoading = isLoading,
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToDetails(dealId: Int) {
    this.navigateSingleTopTo("${Details.route}/$dealId")
}
