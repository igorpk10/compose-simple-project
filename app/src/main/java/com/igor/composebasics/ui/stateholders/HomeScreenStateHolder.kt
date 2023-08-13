package com.igor.composebasics.ui.stateholders

import com.igor.composebasics.data.models.Product

class HomeScreenStateHolder(
    val sections: Map<String, List<Product>>,
    val searchText: String = "",
    val searchProducts: List<Product> = listOf(),
    val onSearchChange: (String) -> Unit = {},
    val onFabClick: () -> Unit = {}
) {
    fun isShowSection(): Boolean = searchText.isNullOrBlank()
}
