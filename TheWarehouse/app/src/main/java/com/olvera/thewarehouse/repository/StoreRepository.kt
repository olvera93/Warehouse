package com.olvera.thewarehouse.repository

import com.olvera.thewarehouse.data.remote.StoreApi
import com.olvera.thewarehouse.model.CategoryModel
import com.olvera.thewarehouse.model.ProductList
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val storeApi: StoreApi
) {

    suspend fun getAllProducts(): List<ProductList>? {
        val response = storeApi.getAllProducts()

        if (response.isSuccessful) {
            return response.body()?.products
        }
        return null
    }

    suspend fun getAllCategories(): List<CategoryModel>? {
        val response = storeApi.getAllCategories()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getProductsByCategory(category: String): List<ProductList>? {
        val response = storeApi.getProductsByCategory(category)
        return if (response.isSuccessful) {
            response.body()?.products
        } else {
            null
        }
    }
}