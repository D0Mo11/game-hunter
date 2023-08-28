package com.dragic.gamehunter.view.gamedetails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.uicomponents.DealDetailsCard
import com.dragic.gamehunter.view.uicomponents.ShimmerFeaturedDeals

@Composable
fun GameDetailsDeals(
    deals: List<DealDetailsViewState>,
    onDealClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    LazyColumn(modifier = modifier) {
        items(deals, key = { it.dealId }) { deal: DealDetailsViewState ->
            ShimmerFeaturedDeals(
                isLoading = isLoading,
                contentAfterLoading = {
                    DealDetailsCard(
                        salePrice = deal.salePrice,
                        normalPrice = deal.normalPrice,
                        savePercentage = deal.savePercentage,
                        storeName = deal.storeName,
                        storeLogo = deal.storeLogoUrl,
                        onDealClick = { onDealClick(deal.dealId) },
                        modifier = Modifier
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.details_deal_card_horizontal_padding),
                                vertical = dimensionResource(id = R.dimen.details_deal_card_vertical_padding),
                            )
                            .height(dimensionResource(id = R.dimen.details_deal_card_height))
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.details_deal_card_radius)))
                    )
                },
                modifier = modifier
            )
        }
    }
}
