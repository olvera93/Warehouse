package com.olvera.thewarehouse.data.remote

import com.olvera.thewarehouse.dto.AuthResponse
import com.olvera.thewarehouse.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WarehouseApi {

    @POST
    suspend fun signUp(@Body user: User): Response<AuthResponse>

}