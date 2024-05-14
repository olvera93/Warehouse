package com.olvera.thewarehouse.model

data class ProductModel(
    val results: List<ProductList>,
    val total: Int? = null,
    val skip: Int? = null,
    val limit: Int?= null
)

data class ProductList(
    val id: Int? = null,
    val title: String? = null,
    val rating: Double? = null,
    val images: List<String> = arrayListOf()
)
