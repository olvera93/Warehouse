package com.olvera.thewarehouse.state

data class PersonState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val matchPassword: String = "",
    val birthDate: String = ""
)
