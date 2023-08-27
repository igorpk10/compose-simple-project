package com.igor.composebasics.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.igor.composebasics.data.datasource.PictureDataSource
import com.igor.composebasics.data.exceptions.EmptyQueryException
import com.igor.composebasics.data.models.PicturePaging
import com.igor.composebasics.data.repositories.PictureRepository
import com.igor.composebasics.domain.models.Picture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPicturesUseCaseImpl @Inject constructor(
    val pictureRepository: PictureRepository
): GetPicturesUseCase {
    override fun invoke(query: String): Flow<PagingData<Picture>> {
        if(query.isNotBlank()) {
            return Pager(
                config = PagingConfig(pageSize = 20, maxSize = 500),
                pagingSourceFactory = { PictureDataSource(pictureRepository, query) }
            ).flow
        }else {
            throw EmptyQueryException()
        }
    }
}