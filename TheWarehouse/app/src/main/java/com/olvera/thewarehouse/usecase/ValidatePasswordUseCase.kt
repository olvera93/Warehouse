package com.olvera.thewarehouse.usecase

class ValidatePasswordUseCase {

    operator fun invoke(password: String): PasswordResult {
        if (password.length !in 8..16) {
            return PasswordResult.INVALID_LENGTH
        }

        if (!password.any { it.isLowerCase() }) {
            return PasswordResult.INVALID_LOWERCASE
        }

        if (!password.any { it.isUpperCase() }) {
            return PasswordResult.INVALID_UPPERCASE
        }

        if (!password.any { it.isDigit() }) {
            return PasswordResult.INVALID_DIGITS
        }
        return PasswordResult.VALID
    }

     fun invokeMatchPassword(password: String, matchPassword: String): PasswordResult {
        if (!password.equals(matchPassword)) {
            return PasswordResult.NOT_MATCH
        }

        return PasswordResult.VALID
    }
}

enum class PasswordResult {
    VALID,
    INVALID_LOWERCASE,
    INVALID_UPPERCASE,
    INVALID_DIGITS,
    INVALID_LENGTH,
    NOT_MATCH
}