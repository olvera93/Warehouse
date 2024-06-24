package com.olvera.thewarehouse.state

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val mobileNumber: String = "",
    val country: String = ""
)
