package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun GameCard(
    gameTitle: String,
    thumbnail: String,
    onGameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.favorite_game_card_image_height))
                    .clickable { onGameClick() },
                model = thumbnail,
                contentDescription = stringResource(id = R.string.favorite_game_content_description),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        top = dimensionResource(id = R.dimen.game_card_title_top_padding),
                        start = dimensionResource(id = R.dimen.game_card_title_horizontal_padding),
                        end = dimensionResource(id = R.dimen.game_card_title_horizontal_padding),
                    ),
                text = gameTitle,
                style = Typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
