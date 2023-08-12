package com.igor.composebasics.data.models

import java.math.BigDecimal

data class Product(
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val image: String? = null
)