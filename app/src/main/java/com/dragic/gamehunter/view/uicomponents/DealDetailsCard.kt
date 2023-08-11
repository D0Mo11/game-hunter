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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.theme.CardContainerColor
import com.dragic.gamehunter.view.theme.Green
import com.dragic.gamehunter.view.theme.Typography

@Composable
fun DealDetailsCard(
    salePrice: String,
    normalPrice: String,
    savePercentage: String,
    storeName: String,
    storeLogo: String,
    onDealClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable { onDealClick() }
            .focusable(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.details_deal_card_radius)),
        colors = CardDefaults.cardColors(containerColor = CardContainerColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.details_deal_card_store_image_padding))
                    .size(dimensionResource(id = R.dimen.details_deal_card_store_image_size))
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                model = storeLogo,
                contentDescription = stringResource(id = R.string.deal_store_image_content_description),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = storeName,
                    style = Typography.titleMedium
                )
                Spacer(modifier = Modifier.size(12.dp))
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
                        text = savePercentage,
                        style = Typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = normalPrice,
                            style = Typography.bodySmall
                        )
                        Spacer(modifier = Modifier.size(8.dp))
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
