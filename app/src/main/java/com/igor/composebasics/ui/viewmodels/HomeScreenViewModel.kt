package com.igor.composebasics.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.composebasics.dao.ProductsDAO
import com.igor.composebasics.data.mock.mockCandies
import com.igor.composebasics.data.mock.mockDrinks
import com.igor.composebasics.data.mock.mockProducts
import com.igor.composebasics.data.models.Product
import com.igor.composebasics.ui.stateholders.HomeScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val productsDAO = ProductsDAO()

    private val _uiState: MutableStateFlow<HomeScreenUIState> = MutableStateFlow(
        HomeScreenUIState()
    )

    val uiState get() =  _uiState.asStateFlow()


    init {
        _uiState.update { currentState ->
            currentState.copy(onSearchChange = {
                _uiState.value = _uiState.value.copy(
                    searchText = it,
                    searchProducts = searchProducts(_uiState.value.sections, it)
                )
            })
        }

        viewModelScope.launch {
            productsDAO.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "All Products" to products,
                        "Food" to mockProducts,
                        "Candies" to mockCandies,
                        "Drinks" to mockDrinks
                    ),
                    searchProducts = searchProducts(
                        _uiState.value.sections,
                        _uiState.value.searchText
                    )
                )
            }
        }
    }


    fun searchProducts(sections: Map<String, List<Product>>, searchText: String) =
        if (!searchText.isNullOrBlank()) {
            sections.entries.map { Pair(it.key, it.value) }
                .toTypedArray().flatMap {
                    it.second
                }.filter {
                    it.name.lowercase().contains(searchText.lowercase()) ||
                            it.description?.lowercase()
                                ?.contains(searchText.lowercase()) ?: false
                }
        } else emptyList()
}