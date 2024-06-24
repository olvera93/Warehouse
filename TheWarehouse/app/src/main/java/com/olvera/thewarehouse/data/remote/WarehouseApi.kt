package com.olvera.thewarehouse.data.remote

import com.olvera.thewarehouse.dto.AuthResponse
import com.olvera.thewarehouse.model.User
import com.olvera.thewarehouse.util.Constants.Companion.BASE_WAREHOUSE_URL
import com.olvera.thewarehouse.util.Constants.Companion.ENDPOINT_SIGNUP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WarehouseApi {

    @POST(BASE_WAREHOUSE_URL+ENDPOINT_SIGNUP)
    suspend fun signUp(@Body user: User): Response<AuthResponse>

}