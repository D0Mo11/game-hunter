package com.dragic.gamehunter.view.uicomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun ImageContent(
    gameTitle: String,
    lowestPrice: String,
    dateLowestPrice: String,
    isFavorite: Boolean,
    onFavoriteSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_content_top_padding)))
        Text(
            text = gameTitle,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.details_title_text_horizontal_padding))
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_text_padding_small)))
        Row {
            Text(
                text = stringResource(id = R.string.lowest_price_text),
                style = Typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.details_title_text_horizontal_padding))
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_text_padding_small)))
            Text(
                text = lowestPrice,
                style = Typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_text_padding_small)))
            Text(
                text = stringResource(id = R.string.details_text_on),
                style = Typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_text_padding_small)))
            Text(
                text = dateLowestPrice,
                style = Typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.details_content_end_padding)))
        Box(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.favorite_icon_start_padding),
                    bottom = dimensionResource(id = R.dimen.favorite_icon_bottom_padding)
                )
                .size(dimensionResource(id = R.dimen.favorite_box_size))
                .clip(CircleShape)
                .background(color = Color.White)
                .bounceClick { onFavoriteSelected() }
        ) {
            Image(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(id = R.string.favorite_icon),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

fun Modifier.bounceClick(
    scaleDown: Float = 0.9f,
    onClick: () -> Unit
) = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatable = remember {
        Animatable(1f)
    }

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            animatable.animateTo(scaleDown)
        } else animatable.animateTo(1f)
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}
