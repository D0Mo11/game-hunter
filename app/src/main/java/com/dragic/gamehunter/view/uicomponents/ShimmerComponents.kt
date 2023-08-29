package com.dragic.gamehunter.view.uicomponents

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier,
) {
    Crossfade(targetState = isLoading, animationSpec = tween(1500)) { loading ->
        if (loading) {
            Card(
                modifier = modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.deal_card_padding),
                        vertical = dimensionResource(id = R.dimen.deal_card_padding)
                    )
                    .height(dimensionResource(id = R.dimen.deal_card_height))
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.deal_card_radius))),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.deal_card_image_width))
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_image_end_padding)))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = dimensionResource(id = R.dimen.deal_card_image_end_padding))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(id = R.dimen.deal_card_title_top_padding))
                                .height(dimensionResource(id = R.dimen.row_height))
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding)))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.row_height))
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding)))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.row_height))
                                .shimmerEffect(),
                        )

                    }
                }
            }
        } else {
            contentAfterLoading()
        }
    }
}

@Composable
fun ShimmerImageContent(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier,
) {
    Crossfade(targetState = isLoading, animationSpec = tween(1500)) { loading ->
        if (loading) {
            Column(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.details_image_height))
                    .fillMaxWidth()
                    .shimmerEffect()
            ) {}
        } else {
            contentAfterLoading()
        }
    }
}

@Composable
fun ShimmerFeaturedDeals(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier,
) {
    Crossfade(targetState = isLoading, animationSpec = tween(1500)) { loading ->
        if (loading) {
            Card(
                modifier = modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.details_deal_card_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.details_deal_card_vertical_padding),
                    )
                    .height(dimensionResource(id = R.dimen.details_deal_card_height))
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.details_deal_card_radius)))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.details_deal_card_store_image_padding))
                            .size(dimensionResource(id = R.dimen.details_deal_card_store_image_size))
                            .clip(CircleShape)
                            .align(Alignment.CenterVertically)
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_deal_card_horizontal_padding)))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = dimensionResource(id = R.dimen.deal_card_image_end_padding))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(id = R.dimen.deal_card_title_top_padding))
                                .height(dimensionResource(id = R.dimen.row_height))
                                .shimmerEffect(),
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding)))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.row_height))
                                .shimmerEffect(),
                        )
                    }
                }
            }

        } else {
            contentAfterLoading()
        }
    }
}

val shimmerColors = listOf(
    Color.LightGray.copy(alpha = 0.6f),
    Color.LightGray.copy(alpha = 0.2f),
    Color.LightGray.copy(alpha = 0.6f),
)

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(800), repeatMode = RepeatMode.Reverse
        )
    )
    background(
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    )
}
