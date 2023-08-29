package com.dragic.gamehunter.view.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.uicomponents.DealCard
import com.dragic.gamehunter.view.uicomponents.ShimmerListItem
import kotlinx.coroutines.delay

@Composable
fun HomeDeals(
    deals: List<DealViewState>,
    onDealClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onPageIncrement: () -> Unit,
    loadNextPage: () -> Unit,
    dealsNotEmpty: () -> Boolean,
) {
    var isLoading by remember { mutableStateOf(true) }
    val scrollState = rememberLazyListState()
    val isAtBottom = !scrollState.canScrollForward

    LaunchedEffect(key1 = true) {
        delay(3000)
        isLoading = false
    }

    LazyColumn(modifier = modifier, state = scrollState) {
        items(deals) { deal: DealViewState ->
            ShimmerListItem(
                isLoading = isLoading,
                contentAfterLoading = {
                    DealCard(
                        gameTitle = deal.gameTitle,
                        salePrice = deal.salePrice,
                        normalPrice = deal.normalPrice,
                        savePercentage = deal.savePercentage,
                        steamRating = deal.steamRating,
                        dealRating = deal.dealRating,
                        thumbnail = deal.thumbnail,
                        onDealClick = { onDealClick(deal.id) },
                        modifier = Modifier
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.deal_card_padding),
                                vertical = dimensionResource(id = R.dimen.deal_card_padding),
                            )
                            .height(dimensionResource(id = R.dimen.deal_card_height))
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.deal_card_radius)))
                    )
                },
                modifier = modifier,
            )
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            if (dealsNotEmpty()) {
                onPageIncrement()
                loadNextPage()
            }
        }
    }
}
