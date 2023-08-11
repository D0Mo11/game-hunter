package com.dragic.gamehunter.networking

import com.dragic.gamehunter.model.StoreInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreInfoResponse(
    @SerialName("storeID")
    val storeId: String,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("images")
    val images: ImageDataResponse,
)

@Serializable
data class ImageDataResponse(
    @SerialName("logo")
    val logoUrl: String,
)

fun StoreInfoResponse.toStoreInfoEntity() = StoreInfoEntity(
    storeId = storeId,
    storeName = storeName,
    logoUrl = images.logoUrl,
)
