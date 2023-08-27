package com.igor.composebasics.domain.mapper

import com.igor.composebasics.data.models.PictureResponse
import com.igor.composebasics.domain.models.Picture

fun PictureResponse.toPicture(): Picture = Picture(
    id = this.id,
    url = this.source.originalSize,
    photographer = this.photographer,
    photographerUrl = this.photographerProfile,
    alt = this.alt
)