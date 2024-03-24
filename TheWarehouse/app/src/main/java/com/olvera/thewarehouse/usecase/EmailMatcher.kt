package com.olvera.thewarehouse.usecase

import android.util.Patterns

interface EmailMatcher {
    fun isValid(email: String): Boolean
}