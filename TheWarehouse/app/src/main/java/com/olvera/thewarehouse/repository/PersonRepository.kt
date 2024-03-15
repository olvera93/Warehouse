package com.olvera.thewarehouse.repository

import com.olvera.thewarehouse.data.remote.PersonApi
import com.olvera.thewarehouse.model.Person
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personApi: PersonApi
) {

    suspend fun signUp(person: Person): Person? {
        val response = personApi.signUp(person)

        if (response.isSuccessful) {
            return response.body()!!
        }

        return null
    }



}