package com.olvera.thewarehouse.presentation.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.olvera.thewarehouse.util.Constants.Companion.toPerson

@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel
) {

    Box(modifier = Modifier.fillMaxSize()) {
        ContentSignUpView(signUpViewModel = signUpViewModel)
    }

    Text(text = "Hello Sign Up")
    
}

@Composable
fun ContentSignUpView(
    signUpViewModel: SignUpViewModel
) {

    val personState = signUpViewModel.state

    Column {
        TextField(
            value = personState.name,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(name = newName)
            }
        )

        TextField(
            value = personState.lastName,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(lastName = newName)
            }
        )

        TextField(
            value = personState.email,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(email = newName)
            }
        )

        TextField(
            value = personState.mobileNumber,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(mobileNumber = newName)
            }
        )

        TextField(
            value = personState.password,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(password = newName)
            }
        )

        TextField(
            value = personState.matchPassword,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(matchPassword = newName)
            }
        )

        TextField(
            value = personState.birthDate,
            onValueChange = { newName ->
                signUpViewModel.state = personState.copy(birthDate = newName)
            }
        )

        Button(onClick = { signUpViewModel.signUp(personState.toPerson()) }) {
            Text(text = "Save")
        }
    }


}