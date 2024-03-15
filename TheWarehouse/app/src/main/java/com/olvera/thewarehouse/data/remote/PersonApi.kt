package com.olvera.thewarehouse.data.remote

import com.olvera.thewarehouse.model.Person
import com.olvera.thewarehouse.util.Constants.Companion.ENDPOINT_SIGNUP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PersonApi {

    @POST(ENDPOINT_SIGNUP)
    suspend fun signUp(@Body person: Person): Response<Person>


}