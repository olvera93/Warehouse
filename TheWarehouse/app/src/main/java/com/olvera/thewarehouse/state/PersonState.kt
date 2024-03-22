package com.olvera.thewarehouse.state

data class PersonState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val matchPassword: String = "",
    val birthDate: String = "",
    val nameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val mobileNumberError: String? = null,
    val passwordError: String? = null,
    val matchPasswordError: String? = null,
    val birthDateError: String? = null,
    val timestamp: String? = "",
    val status: Int = 0,
    val error: String? = "",
    val trace: String? = "",
    val message: String? = "",
    val path: String? = ""
)