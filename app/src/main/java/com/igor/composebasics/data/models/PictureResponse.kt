package com.igor.composebasics.data.models

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("photographer")
    val photographer: String,

    @SerializedName("photographer_url")
    val photographerProfile: String,

    @SerializedName("photographer_id")
    val photographerId: Int,

    @SerializedName("avg_color")
    val avgColor: String,

    @SerializedName("src")
    val source: PictureSourceResponse,

    @SerializedName("liked")
    val liked: Boolean,

    @SerializedName("alt")
    val alt: String
)

data class PictureSourceResponse(
    @SerializedName("original")
    val originalSize: String,
    @SerializedName("large2x")
    val large2xSize: String,
    @SerializedName("large")
    val largelSize: String,
    @SerializedName("medium")
    val mediumSize: String,
    @SerializedName("small")
    val smallSize: String,
    @SerializedName("portrait")
    val portraitSize: String,
    @SerializedName("landscape")
    val landscapeSize: String,
    @SerializedName("tiny")
    val tinySize: String,
)
