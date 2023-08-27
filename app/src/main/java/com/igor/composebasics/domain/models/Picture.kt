package com.igor.composebasics.domain.models

data class Picture(
    val id: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val alt: String,
)
