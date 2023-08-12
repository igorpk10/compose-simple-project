package com.igor.composebasics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.igor.composebasics.data.mock.sections
import com.igor.composebasics.data.models.Product
import com.igor.composebasics.ui.components.CardProductItem
import com.igor.composebasics.ui.components.ProductsSection
import com.igor.composebasics.ui.components.SearchTextField
import com.igor.composebasics.ui.theme.ComposeBasicsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = "",
    onFabClick: () -> Unit
) {
    var text by remember {
        mutableStateOf(searchText)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null )
            }
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column {
                SearchTextField(text) {
                    text = it
                }

                if (text.isNullOrBlank()) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {

                        for (section in sections) {
                            val title = section.key
                            val products = section.value

                            item {
                                ProductsSection(
                                    title = title,
                                    products = products
                                )
                            }
                        }
                    }
                } else {
                    val list: ArrayList<Product> = arrayListOf()
                    sections.forEach { mapValues ->
                        list.addAll(mapValues.value.filter {
                            it.name.lowercase().contains(text.lowercase())
                        })
                    }

                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(list) {
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
            HomeScreen(sections){

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewFiltered() {
    ComposeBasicsTheme {
        Surface {
            HomeScreen(sections, "Chocolate"){

            }
        }
    }
}