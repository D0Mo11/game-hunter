package com.dragic.gamehunter.view.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.uicomponents.DealCard
import com.dragic.gamehunter.view.uicomponents.ShimmerListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter

@Composable
fun HomeDeals(
    deals: List<DealViewState>,
    onDealClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    onPageIncrement: () -> Unit,
    loadNextPage: () -> Unit,
) {
    var isLoading by remember { mutableStateOf(true) }

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

    if (deals.isNotEmpty()) {
        LaunchedEffect(scrollState) {
            snapshotFlow { scrollState.isScrollInProgress }
                .filter { !it }
                .collect {
                    val totalItems = deals.size
                    val lastVisibleItemIndex = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1

                    if (lastVisibleItemIndex >= totalItems - 1) {
                        onPageIncrement()
                        loadNextPage()
                    }
                }
        }
    }
}
