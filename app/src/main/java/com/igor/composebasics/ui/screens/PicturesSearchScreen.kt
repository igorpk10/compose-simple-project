package com.igor.composebasics.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.igor.composebasics.R
import com.igor.composebasics.domain.models.Picture
import com.igor.composebasics.ui.components.SearchTextField
import com.igor.composebasics.ui.stateholders.PictureScreenUIState
import com.igor.composebasics.ui.viewmodels.PictureSearchViewModel

@Composable
fun PictureSearchScreen(
    viewModel: PictureSearchViewModel,
) {
    val pictureState by viewModel.uiState.collectAsState()
    PictureSearchScreen(pictureState)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PictureSearchScreen(
    state: PictureScreenUIState,
) {
    val lazyPictures = state.picturesFlow.collectAsLazyPagingItems()

    Scaffold {
        Column {
            SearchTextField(
                searchText = state.query,
                onSearchDone = state.onSearchDone,
                onSearchChanged = state.onSearchChange
            )

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(
                    count = lazyPictures.itemCount,
                    key = lazyPictures.itemKey { it.id }
                ) {
                    lazyPictures[it]?.let {
                        AsyncImage(
                            modifier = Modifier.height(200.dp),
                            model = it.url,
                            contentDescription = it.alt,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.placeholder)
                        )
                    }
                }

            }
        }
    }
}