package com.igor.composebasics.domain.usecases

import androidx.paging.PagingData
import com.igor.composebasics.data.models.PicturePaging
import com.igor.composebasics.domain.models.Picture
import kotlinx.coroutines.flow.Flow

interface GetPicturesUseCase {

    fun invoke (query: String): Flow<PagingData<Picture>>

}