package com.olvera.thewarehouse.state

data class ProductsState(
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val category: String = "",
    val images: List<String>? = arrayListOf()
)
