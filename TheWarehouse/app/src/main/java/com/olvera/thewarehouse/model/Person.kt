package com.olvera.thewarehouse.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val lastName: String,
    val email: String,
    val mobileNumber: String,
    val password: String,
    val matchPassword: String,
    val birthDate: String
): Parcelable