package com.igor.composebasics.ui.stateholders

import com.igor.composebasics.data.models.Product

data class HomeScreenUIState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchText: String = "",
    val searchProducts: List<Product> = listOf(),
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSection(): Boolean = searchText.isNullOrBlank()
}
