package com.olvera.thewarehouse.presentation.screens.signup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.R
import com.olvera.thewarehouse.model.Person
import com.olvera.thewarehouse.repository.PersonRepository
import com.olvera.thewarehouse.state.PersonState
import com.olvera.thewarehouse.usecase.SignupUseCases
import com.olvera.thewarehouse.util.Constants.Companion.parseError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val useCases: SignupUseCases,
    private val context: Context
) : ViewModel() {

    var state by mutableStateOf(PersonState())
        private set

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.NameChange -> {
                state = state.copy(
                    name = event.name,
                    nameError = null
                )
            }

            is SignupEvent.LastNameChange -> {
                state = state.copy(
                    lastName = event.lastName,
                    lastNameError = null

                )
            }

            is SignupEvent.EmailChange -> {
                state = state.copy(
                    email = event.email,
                    emailError = null
                )
            }

            is SignupEvent.MobileNumberChange -> {
                state = state.copy(
                    mobileNumber = event.mobileNumber,
                    mobileNumberError = null
                )
            }

            is SignupEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password,
                    passwordError = null
                )
            }

            is SignupEvent.MatchPasswordChange -> {
                state = state.copy(
                    matchPassword = event.matchPasswordChange,
                    mobileNumberError = null
                )
            }

            is SignupEvent.BirthDateChange -> {
                state = state.copy(
                    birthDate = event.birthDateChange,
                    birthDateError = null
                )
            }

            is SignupEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {

        state = state.copy(
            nameError = null,
            lastNameError = null,
            emailError = null,
            mobileNumberError = null,
            passwordError = null,
            matchPasswordError = null,
            birthDateError = null
        )

        when {
            useCases.validEmptyFields(state.name) -> {
                state = state.copy(
                    nameError = context.getString(R.string.name_error)
                )
            }

            useCases.validEmptyFields(state.lastName) -> {
                state = state.copy(
                    lastNameError = context.getString(R.string.last_name_error)
                )
            }

            useCases.validEmptyFields(state.email) -> {
                state = state.copy(
                    emailError = context.getString(R.string.email_error)
                )
            }

            useCases.validEmptyFields(state.mobileNumber) -> {
                state = state.copy(
                    mobileNumberError = context.getString(R.string.mobile_number_error)
                )
            }

            useCases.validEmptyFields(state.password) -> {
                state = state.copy(
                    passwordError = context.getString(R.string.password_error)
                )
            }

            useCases.validEmptyFields(state.matchPassword) -> {
                state = state.copy(
                    matchPasswordError = context.getString(R.string.match_password_error)
                )
            }

            useCases.validEmptyFields(state.birthDate) -> {
                state = state.copy(
                    birthDateError = context.getString(R.string.birthdate_error)
                )
            }
        }

        if (!useCases.validateEmailUseCase.invoke(state.email)) {
            state = state.copy(
                emailError = context.getString(R.string.email_valid)
            )
        }

        val passwordResult = useCases.validatePasswordUseCase(state.password)
        state = state.copy(
            passwordError = parseError(passwordResult)
        )

        val matchPassword =
            useCases.validatePasswordUseCase.invokeMatchPassword(state.password, state.matchPassword)
        state = state.copy(
            matchPasswordError = parseError(matchPassword)
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (areAllFieldsFilled(state)) {
                        val result = Person(
                            name = state.name,
                            lastName = state.lastName,
                            email = state.email,
                            mobileNumber = state.mobileNumber,
                            password = state.password,
                            matchPassword = state.matchPassword,
                            birthDate = state.birthDate,
                        )
                        personRepository.signUp(result)
                    }


                } catch (e: Exception) {
                    print(e.message)
                }
            }
        }
    }

    private fun areAllFieldsFilled(state: PersonState): Boolean {
        return state.nameError == null &&
                state.lastNameError == null &&
                state.emailError == null &&
                state.mobileNumberError == null &&
                state.passwordError == null &&
                state.matchPasswordError == null &&
                state.birthDateError == null
    }

    fun clean() {
        state = state.copy(
            name = "",
            lastName = "",
            email = "",
            mobileNumber = "",
            password = "",
            matchPassword = "",
            birthDate = ""
        )
    }
}