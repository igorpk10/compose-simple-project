package com.igor.composebasics.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.igor.composebasics.data.models.PictureResponse
import com.igor.composebasics.domain.models.Picture
import com.igor.composebasics.data.repositories.PictureRepository
import com.igor.composebasics.domain.mapper.toPicture
import retrofit2.HttpException
import java.io.IOException

class PictureDataSource(
    private val pictureRepository: PictureRepository,
    private val query: String
) : PagingSource<Int, Picture>() {
    companion object {
        private const val LIMIT = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val position = params.key ?: 1
        val queries = hashMapOf(
            "query" to query,
            "page" to position.toString(),
            "per_page" to LIMIT.toString()
        )

        return try {
            val picturesPaging = pictureRepository.fetchPictures(queries)
            val pictures = picturesPaging.pictures

            val prevKey = if (position == 1) null else position - 1
            val nextKey = if (pictures.isEmpty()) {
                null
            } else {
                position + LIMIT
            }


            LoadResult.Page(
                data = pictures.map {
                    it.toPicture()
                },
                prevKey = prevKey,
                nextKey = nextKey,

                )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }
}