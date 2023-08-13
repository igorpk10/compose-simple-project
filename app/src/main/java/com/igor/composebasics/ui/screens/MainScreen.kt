package com.igor.composebasics.ui.screens

import android.content.Intent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igor.composebasics.data.mock.mockCandies
import com.igor.composebasics.data.mock.mockDrinks
import com.igor.composebasics.data.mock.mockSections
import com.igor.composebasics.data.models.Product
import com.igor.composebasics.ui.components.CardProductItem
import com.igor.composebasics.ui.components.ProductsSection
import com.igor.composebasics.ui.components.SearchTextField
import com.igor.composebasics.ui.stateholders.HomeScreenStateHolder
import com.igor.composebasics.ui.theme.ComposeBasicsTheme


@Composable
fun HomeScreen(
    products: List<Product>,
    onFabClick: () -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    val sections = mapOf(
        "All Products" to products,
        "Candies" to mockCandies,
        "Drinks" to mockDrinks
    )


    val searchProducts = remember(text, products) {
        if (!text.isNullOrBlank()) {
            sections.entries.map { Pair(it.key, it.value) }
                .toTypedArray().flatMap {
                    it.second
                }.filter {
                    it.name.lowercase().contains(text.lowercase())
                }
        } else emptyList()
    }

    val homeState = remember(products, text) {
        HomeScreenStateHolder(
            sections = sections,
            searchProducts = searchProducts,
            searchText = text,
            onSearchChange = {
                text = it
            },
            onFabClick = onFabClick)
    }

    HomeScreen(homeState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenStateHolder
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(shape = CircleShape, onClick = state.onFabClick) {
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
            HomeScreen(HomeScreenStateHolder(sections = mockSections) {
            })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewFiltered() {
    ComposeBasicsTheme {
        Surface {
            HomeScreen(HomeScreenStateHolder(mockSections, "Chocolate") {
            })
        }
    }
}