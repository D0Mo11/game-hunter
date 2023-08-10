package com.dragic.gamehunter.view.gamedetails

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.R)
fun GameDetailsDeal.toDealDetailsViewState() = DealDetailsViewState(
    dealId = dealId,
    storeId = storeId,
    storeName = storeName,
    storeLogoUrl = storeLogoUrl,
    savePercentage = "-${savingsToPercentage(savings)} OFF",
    salePrice = priceToUsd(salePrice),
    normalPrice = priceToUsd(normalPrice),
)
