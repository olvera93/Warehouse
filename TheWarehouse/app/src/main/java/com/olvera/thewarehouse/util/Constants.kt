package com.olvera.thewarehouse.util

import com.olvera.thewarehouse.model.Person
import com.olvera.thewarehouse.state.PersonState

class Constants {

    companion object {

        const val BASE_PERSON_URL = "http://10.0.2.2:8080/api/v1/"
        const val ENDPOINT_SIGNUP = "signUp"

        const val BASE_STORE_URL = "https://dummyjson.com/docs/"
        const val ENDPOINT_PRODUCTS = "products"

        fun PersonState.toPerson(): Person {
            return Person(
                name = this.name,
                lastName = this.lastName,
                email = this.email,
                mobileNumber = this.mobileNumber,
                password = this.password,
                matchPassword = this.matchPassword,
                birthDate = this.birthDate
            )
        }

    }
}