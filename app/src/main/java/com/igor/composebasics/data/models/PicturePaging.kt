package com.igor.composebasics.data.models

import com.google.gson.annotations.SerializedName
import com.igor.composebasics.domain.models.Picture

data class PicturePaging(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val total: Int,
    @SerializedName("per_page")
    val itensCount: Int,
    @SerializedName("photos")
    val pictures: List<PictureResponse>
)
