package com.dragic.gamehunter.view.gamedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography
import com.dragic.gamehunter.view.uicomponents.GameDetailsTopBar
import com.dragic.gamehunter.view.uicomponents.ImageContent
import com.dragic.gamehunter.view.uicomponents.ImageWithGradient

@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    imageContentState: ImageContentViewState?,
    dealDetailsState: List<DealDetailsViewState>,
    onFavoriteClick: () -> Unit,
    onDealClick: (String) -> Unit,
    onBackArrowClick: () -> Unit,
) {
    Column {
        GameDetailsTopBar(
            onArrowBackClicked = { onBackArrowClick() }
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.details_image_height))
        ) {
            if (imageContentState != null) {
                ImageWithGradient(
                    thumbnail = imageContentState.thumbnail,
                    content = {
                        with(imageContentState) {
                            ImageContent(
                                gameTitle = gameTitle,
                                lowestPrice = lowestPrice,
                                dateLowestPrice = dateLowestPrice,
                                isFavorite = isFavorite,
                                onFavoriteSelected = { onFavoriteClick() },
                            )
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.details_text_padding),
                    end = dimensionResource(id = R.dimen.details_text_padding),
                    bottom = dimensionResource(id = R.dimen.details_text_padding),
                ),
            text = stringResource(id = R.string.featured_deals),
            style = Typography.labelLarge
        )
        GameDetailsDeals(
            deals = dealDetailsState,
            onDealClick = { onDealClick(it) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
