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

    fun signUp(person: Person) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = personRepository.signUp(person)
                state = state.copy(
                    name = result?.name ?: "",
                    lastName = result?.lastName ?: "",
                    email = result?.email ?: "",
                    mobileNumber = result?.mobileNumber ?: "",
                    password = result?.password ?: "",
                    matchPassword = result?.matchPassword ?: "",
                    birthDate = result?.birthDate ?: "",
                )
            }
        }
    }

}