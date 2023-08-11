package com.dragic.gamehunter.view.gamedetails

import com.dragic.gamehunter.model.GameDetailsDeal
import com.dragic.gamehunter.utils.priceToUsd
import com.dragic.gamehunter.utils.savingsToPercentage

data class DealDetailsViewState(
    val dealId: String,
    val storeId: String,
    val storeName: String,
    val storeLogoUrl: String,
    val savePercentage: String,
    val salePrice: String,
    val normalPrice: String,
)

fun GameDetailsDeal.toDealDetailsViewState() = DealDetailsViewState(
    dealId = dealId,
    storeId = storeId,
    storeName = storeName,
    storeLogoUrl = storeLogoUrl,
    savePercentage = "-${savingsToPercentage(savings)} OFF",
    salePrice = priceToUsd(salePrice),
    normalPrice = priceToUsd(normalPrice),
)
