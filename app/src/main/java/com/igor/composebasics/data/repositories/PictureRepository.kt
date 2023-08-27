package com.igor.composebasics.data.repositories

import com.igor.composebasics.data.models.PicturePaging
import com.igor.composebasics.domain.models.Picture
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PictureRepository {
    @GET("search/")
    suspend fun fetchPictures(
        @QueryMap queries: Map<String, String>
    ): PicturePaging
}