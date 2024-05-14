package com.olvera.thewarehouse.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val mobileNumber: String,
    val country: String

): Parcelable