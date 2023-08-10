package com.dragic.gamehunter.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.dragic.gamehunter.R
import com.dragic.gamehunter.view.uicomponents.HomeTopBar

@Composable
fun HomeScreen(
    onDealClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    dealState: List<DealViewState>,
    onSortByDealRatingClick: () -> Unit,
    onSortBySavingsClick: () -> Unit,
    onSortByReviewsClick: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            HomeTopBar(
                onSortCLicked = { showDialog = true }
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.home_text_padding),
                        start = dimensionResource(id = R.dimen.home_top_deals_label_horizontal_padding),
                        end = dimensionResource(id = R.dimen.home_top_deals_label_horizontal_padding),
                        bottom = dimensionResource(id = R.dimen.text_padding_medium),
                    ),
                text = stringResource(id = R.string.top_deals),
                style = MaterialTheme.typography.labelLarge
            )
            HomeDeals(
                deals = dealState,
                onDealClick = { id ->
                    onDealClick(id)
                },
                modifier = modifier.fillMaxWidth(),
            )
        }
        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.home_dialog_box_padding))
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_dialog_sort_by),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.text_padding_medium))
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_deal_rating),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    onSortByDealRatingClick()
                                    showDialog = false
                                },
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_savings),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    onSortBySavingsClick()
                                    showDialog = false
                                }
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                        Text(
                            text = stringResource(id = R.string.home_dialog_reviews),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.text_padding_small))
                                .clickable {
                                    onSortByReviewsClick()
                                    showDialog = false
                                }
                        )
                        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.text_padding_medium)))
                    }
                }
            }
        }
    }
}
