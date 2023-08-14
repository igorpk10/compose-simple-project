package com.igor.composebasics.dao

import androidx.compose.runtime.mutableStateListOf
import com.igor.composebasics.data.mock.mockProducts
import com.igor.composebasics.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductsDAO {

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun products() = products.asStateFlow()

    fun save(product: Product) {
        products.value = products.value + product
    }
}