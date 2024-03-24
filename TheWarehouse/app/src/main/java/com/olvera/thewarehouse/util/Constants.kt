package com.olvera.thewarehouse.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.olvera.thewarehouse.model.Person
import com.olvera.thewarehouse.state.PersonState
import com.olvera.thewarehouse.usecase.PasswordResult

class Constants {

    companion object {

        const val BASE_PERSON_URL = "http://192.168.0.8:8080/api/v1/"
        const val ENDPOINT_SIGNUP = "signUp"

        const val BASE_STORE_URL = "https://dummyjson.com/docs/"
        const val ENDPOINT_PRODUCTS = "products"

        fun parseError(error: PasswordResult): String? {
            return when (error) {
                PasswordResult.VALID -> null
                PasswordResult.INVALID_LOWERCASE -> "The password must have at least 1 lowercase character."
                PasswordResult.INVALID_UPPERCASE -> "The password must have at least 1 uppercase character."
                PasswordResult.INVALID_DIGITS -> "The password must have at least 1 number."
                PasswordResult.INVALID_LENGTH -> "The password must be at least 8 characters long"
                PasswordResult.NOT_MATCH -> "Passwords don't match, please check them"
            }
        }

    }
}

