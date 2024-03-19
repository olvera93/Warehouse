package com.olvera.thewarehouse.presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olvera.thewarehouse.model.Person
import com.olvera.thewarehouse.repository.PersonRepository
import com.olvera.thewarehouse.state.PersonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val personRepository: PersonRepository
): ViewModel() {

    var state by mutableStateOf(PersonState())
        private set


    fun onEvent(event: SignupEvent) {
        when(event) {
            is SignupEvent.NameChange -> {
                state = state.copy(
                    name = event.name
                )
            }

            is SignupEvent.LastNameChange -> {
                state = state.copy(
                    lastName = event.lastName
                )
            }

            is SignupEvent.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }

            is SignupEvent.MobileNumberChange -> {
                state = state.copy(
                    mobileNumber = event.mobileNumber
                )
            }

            is SignupEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }

            is SignupEvent.MatchPasswordChange -> {
                state = state.copy(
                    matchPassword = event.matchPasswordChange
                )
            }

            is SignupEvent.BirthDateChange -> {
                state = state.copy(
                    birthDate = event.birthDateChange
                )
            }

            is SignupEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
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
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }

    fun clean() {
        state = state.copy(
            name = "",
            lastName = "",
            email = "",
            mobileNumber = "",
            password = "",
            matchPassword =  "",
            birthDate = "",
        )
    }

}