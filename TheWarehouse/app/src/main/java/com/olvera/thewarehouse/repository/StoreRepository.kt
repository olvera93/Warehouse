package com.olvera.thewarehouse.repository

import com.olvera.thewarehouse.data.remote.StoreApi
import com.olvera.thewarehouse.model.ProductList
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val storeApi: StoreApi
) {

    suspend fun getAllProducts(): List<ProductList>? {
        val response = storeApi.getAllProducts()

        if (response.isSuccessful) {
            return response.body()?.results
        }

        return null
    }


}