package com.igor.composebasics.ui.states

import androidx.paging.PagingData
import com.igor.composebasics.domain.models.Picture

sealed class SearchImagesStates {
    object Loading : SearchImagesStates()
    data class Success(val data: PagingData<Picture>) : SearchImagesStates()

    object Error: SearchImagesStates()

}
