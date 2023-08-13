package com.igor.composebasics.dao

import androidx.compose.runtime.mutableStateListOf
import com.igor.composebasics.data.mock.mockProducts
import com.igor.composebasics.data.models.Product

class ProductsDAO {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = (products + mockProducts).toList()

    fun save(product: Product) {
        products.add(product)
    }
}