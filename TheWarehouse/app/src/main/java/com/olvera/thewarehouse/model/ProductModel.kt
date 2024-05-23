package com.olvera.thewarehouse.model

data class ProductModel(
    val products: List<ProductList>,
    val total: Int? = null,
    val skip: Int? = null,
    val limit: Int?= null
)

data class ProductList(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val price: Double? = null,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val tags: List<String> = arrayListOf(),
    val brand: String? = null,
    val sku: String? = null,
    val weight: Int? = null,
    val dimensions: Dimensions? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val reviews: List<Review> = arrayListOf(),
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val meta: Meta? = null,
    val thumbnail: String? = null,
    val images: List<String> = arrayListOf()
)

data class Dimensions(
    val width: Double? = null,
    val height: Double? = null,
    val depth: Double? = null
)

data class Review(
    val rating: Int? = null,
    val comment: String? = null,
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null
)

data class Meta(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val barcode: String? = null,
    val qrCode: String? = null
)
