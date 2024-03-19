package com.olvera.thewarehouse.presentation.screens.signup

sealed interface SignupEvent {

    data class NameChange(val name: String): SignupEvent

    data class LastNameChange(val lastName: String): SignupEvent

    data class MobileNumberChange(val mobileNumber: String): SignupEvent

    data class EmailChange(val email: String) : SignupEvent
    data class PasswordChange(val password: String) : SignupEvent

    data class MatchPasswordChange(val matchPasswordChange: String): SignupEvent

    data class BirthDateChange(val birthDateChange: String): SignupEvent

    object SignUp : SignupEvent

}