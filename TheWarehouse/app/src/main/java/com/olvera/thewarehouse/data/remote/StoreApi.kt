package com.olvera.thewarehouse.data.remote

import com.olvera.thewarehouse.model.CategoryModel
import com.olvera.thewarehouse.model.ProductModel
import com.olvera.thewarehouse.util.Constants.Companion.BASE_STORE_URL
import com.olvera.thewarehouse.util.Constants.Companion.ENDPOINT_CATEGORIES
import com.olvera.thewarehouse.util.Constants.Companion.ENDPOINT_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    @GET(BASE_STORE_URL + ENDPOINT_PRODUCTS)
    suspend fun getAllProducts(): Response<ProductModel>

    @GET("$BASE_STORE_URL$ENDPOINT_PRODUCTS/$ENDPOINT_CATEGORIES")
    suspend fun getAllCategories(): Response<List<CategoryModel>>

    @GET("$ENDPOINT_PRODUCTS/$ENDPOINT_CATEGORIES/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Response<ProductModel>
}