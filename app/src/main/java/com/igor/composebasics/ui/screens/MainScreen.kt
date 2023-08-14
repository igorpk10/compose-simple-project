package com.igor.composebasics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igor.composebasics.data.mock.mockSections
import com.igor.composebasics.ui.components.CardProductItem
import com.igor.composebasics.ui.components.ProductsSection
import com.igor.composebasics.ui.components.SearchTextField
import com.igor.composebasics.ui.stateholders.HomeScreenUIState
import com.igor.composebasics.ui.theme.ComposeBasicsTheme
import com.igor.composebasics.ui.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    onFabClick: () -> Unit
) {
    val homeState by homeScreenViewModel.uiState.collectAsState()
    HomeScreen(homeState, onFabClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenUIState,
    onFabClick: () -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = onFabClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column {
                SearchTextField(state.searchText, state.onSearchChange)

                if (state.isShowSection()) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        state.sections.forEach { title, products ->
                            if (!products.isNullOrEmpty()) {
                                val title = title

                                item {
                                    ProductsSection(
                                        title = title,
                                        products = products
                                    )
                                }
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(state.searchProducts) {
                            CardProductItem(product = it, Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }

            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewSections() {
    ComposeBasicsTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUIState(
                    sections = mockSections
                )
            ) {

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewFiltered() {
    ComposeBasicsTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUIState(
                    sections = mockSections, searchText = "hamburguer",
                )
            ) {

            }
        }
    }
}