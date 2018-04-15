package com.loki.core.model.repository.pixabay.pojo

import com.google.gson.annotations.SerializedName

data class ImageEntity(
        @SerializedName("previewURL")
        val previewUrl: String,
        @SerializedName("webformatURL")
        val fullImageUrl: String
)
data class PixaBayPage(
        @SerializedName("hits")
        val images: List<ImageEntity>,
        @SerializedName("totalHits")
        val amount: Int
)