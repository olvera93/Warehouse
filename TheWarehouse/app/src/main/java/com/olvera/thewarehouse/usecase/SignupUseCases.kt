package com.olvera.thewarehouse.usecase

data class SignupUseCases(
    val validEmptyFields: ValidEmptyFields,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateEmailUseCase: ValidateEmailUseCase
)
