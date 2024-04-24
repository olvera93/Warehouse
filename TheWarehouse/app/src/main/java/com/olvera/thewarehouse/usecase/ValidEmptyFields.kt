package com.olvera.thewarehouse.usecase

class ValidEmptyFields {
    operator fun invoke(field: String?): Boolean {
        return field.isNullOrEmpty()
    }

}