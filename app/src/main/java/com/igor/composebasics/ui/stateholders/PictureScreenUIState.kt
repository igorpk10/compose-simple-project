package com.igor.composebasics.ui.stateholders

import androidx.paging.DataSource
import androidx.paging.PagingData
import com.igor.composebasics.data.models.PicturePaging
import com.igor.composebasics.domain.models.Picture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PictureScreenUIState(
    var picturesFlow: Flow<PagingData<Picture>> = emptyFlow(),
    val query: String = "",
    val onSearchDone: () -> Unit = {},
    val onSearchChange: (String) -> Unit = {}
)
