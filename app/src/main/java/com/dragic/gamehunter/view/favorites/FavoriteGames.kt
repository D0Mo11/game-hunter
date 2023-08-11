package com.dragic.gamehunter.view.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.uicomponents.GameCard

@Composable
fun FavoriteGames(
    favoriteGames: List<FavoriteGameViewState>,
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favorite_game_horizontal_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favorite_game_vertical_padding))
    ) {
        items(favoriteGames, key = { it.dealId }) { favoriteGame ->
            GameCard(
                gameTitle = favoriteGame.gameTitle,
                thumbnail = favoriteGame.thumbnail,
                onGameClick = { onGameClick(favoriteGame.dealId) },
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.favorite_game_card_height))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.favorite_game_card_radius)))
            )
        }
    }
}
