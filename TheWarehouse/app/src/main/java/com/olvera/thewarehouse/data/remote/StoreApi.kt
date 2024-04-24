package com.olvera.thewarehouse.data.remote

import com.olvera.thewarehouse.model.ProductModel
import com.olvera.thewarehouse.util.Constants.Companion.BASE_STORE_URL
import com.olvera.thewarehouse.util.Constants.Companion.ENDPOINT_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET(BASE_STORE_URL + ENDPOINT_PRODUCTS)
    suspend fun getAllProducts(): Response<ProductModel>

}