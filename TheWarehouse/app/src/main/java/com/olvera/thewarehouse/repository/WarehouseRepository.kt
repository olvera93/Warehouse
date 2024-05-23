package com.olvera.thewarehouse.repository

import com.olvera.thewarehouse.data.remote.WarehouseApi
import com.olvera.thewarehouse.dto.AuthResponse
import com.olvera.thewarehouse.model.User
import retrofit2.Response
import javax.inject.Inject

class WarehouseRepository @Inject constructor(
    private val warehouseApi: WarehouseApi
) {
    suspend fun createUserProfile(user: User): AuthResponse? {
        val response = warehouseApi.signUp(user)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}