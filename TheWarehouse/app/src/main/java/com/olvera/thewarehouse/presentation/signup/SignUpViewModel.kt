package com.olvera.thewarehouse.presentation.signup

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.model.User
import com.olvera.thewarehouse.repository.WarehouseRepository
import com.olvera.thewarehouse.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val warehouseRepository: WarehouseRepository
): ViewModel() {

    var formState by mutableStateOf(SignUpState())
        private set

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState


    fun onUsernameChange(newUsername: String) {
        formState = formState.copy(username = newUsername)
    }

    fun onPasswordChange(newPassword: String) {
        formState = formState.copy(password = newPassword)
    }

    fun onFirstnameChange(newFirstname: String) {
        formState = formState.copy(firstname = newFirstname)
    }

    fun onLastnameChange(newLastname: String) {
        formState = formState.copy(lastname = newLastname)
    }

    fun onEmailChange(newEmail: String) {
        formState = formState.copy(email = newEmail)
    }

    fun onMobileNumberChange(newMobileNumber: String) {
        formState = formState.copy(mobileNumber = newMobileNumber)
    }

    fun onCountryChange(newCountry: String) {
        formState = formState.copy(country = newCountry)
    }

    fun createUser() {
        viewModelScope.launch {
            val user = User(
                username = formState.username,
                password = formState.password,
                firstname = formState.firstname,
                lastname = formState.lastname,
                email = formState.email,
                mobileNumber = formState.mobileNumber,
                country = formState.country
            )

            _uiState.value = SignUpUiState.Loading

            if (user.username.isEmpty() || user.password.isEmpty() || user.firstname.isEmpty() ||
                user.lastname.isEmpty() || user.email.isEmpty() || user.mobileNumber.isEmpty() ||
                user.country.isEmpty()) {
                _uiState.value = SignUpUiState.Error("Please fill in all fields")
                return@launch
            }

            if (!validatePassword(user.password)) {
                _uiState.value = SignUpUiState.Error("Password must be at least 8 characters long and contain at least one letter and one digit")
                return@launch
            }

            if (!validateEmail(user.email)) {
                _uiState.value = SignUpUiState.Error("Invalid email format")
                return@launch
            }

            val response = warehouseRepository.createUserProfile(user)

            if (response != null) {
                _uiState.value = SignUpUiState.Success
            } else {
                _uiState.value = SignUpUiState.Error("Failed to create user")
            }
        }
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
    }

    private fun validateEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }

}

sealed class SignUpUiState {
    object Idle: SignUpUiState()
    object Loading: SignUpUiState()
    object Success: SignUpUiState()
    data class Error(val message: String): SignUpUiState()
}