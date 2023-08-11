package com.dragic.gamehunter.view.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.CardContainerColor
import com.dragic.gamehunter.view.theme.Green
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun DealCard(
    gameTitle: String,
    salePrice: String,
    normalPrice: String,
    savePercentage: String,
    steamRating: String,
    dealRating: String,
    thumbnail: String,
    onDealClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable { onDealClick() }
            .focusable(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.deal_card_radius)),
        colors = CardDefaults.cardColors(containerColor = CardContainerColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier.width(dimensionResource(id = R.dimen.deal_card_image_width)),
                model = thumbnail,
                contentDescription = stringResource(id = R.string.deal_image_content_description),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_image_end_padding)))
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = dimensionResource(id = R.dimen.deal_card_title_top_padding)),
                    text = gameTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.titleSmall
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = stringResource(id = R.string.deal_card_rating_label),
                            fontSize = dimensionResource(id = R.dimen.deal_card_text_size).value.sp
                        )
                        Text(
                            text = dealRating,
                            style = Typography.bodyMedium
                        )
                    }
                    Row(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.deal_card_padding))
                    ) {
                        Text(
                            text = stringResource(id = R.string.deal_card_steam_rating_label),
                            fontSize = dimensionResource(id = R.dimen.deal_card_text_size).value.sp
                        )
                        Text(
                            text = steamRating,
                            style = Typography.bodyMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .background(
                                color = Green,
                                shape = RectangleShape
                            )
                            .padding(horizontal = dimensionResource(id = R.dimen.deal_card_save_percentage_padding)),
                        text = stringResource(id = R.string.savings_template, savePercentage),
                        style = Typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.deal_card_text_padding_horizontal))
                    ) {
                        Text(
                            text = normalPrice,
                            style = Typography.bodySmall
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.deal_card_text_padding_small)))
                        Text(
                            text = salePrice,
                            style = Typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
