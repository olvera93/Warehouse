package com.olvera.thewarehouse.presentation.screens.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.olvera.thewarehouse.presentation.components.WareDateTextField
import com.olvera.thewarehouse.presentation.components.WarePasswordTextField
import com.olvera.thewarehouse.presentation.components.WareTextField
import com.olvera.thewarehouse.state.PersonState
import java.util.Calendar
import java.util.Date

@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel,
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxSize()) {
        val personState = signUpViewModel.state
        SignUpForm(
            personState,
            signUpViewModel::onEvent,
            modifier = Modifier.fillMaxWidth(),
            navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpForm(
    state: PersonState,
    onEvent: (SignupEvent) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val pickerState = rememberDatePickerState()

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Create your account".uppercase(),
            modifier = modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        WareTextField(
            value = state.name,
            onValueChange = { onEvent(SignupEvent.NameChange(it)) },
            placeholder = "Name",
            contentDescription = "Enter name",
            message = state.message,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Person,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )

        WareTextField(
            value = state.lastName,
            onValueChange = { onEvent(SignupEvent.LastNameChange(it)) },
            placeholder = "Last name",
            contentDescription = "Enter last name",
            message = state.lastNameError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Person,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )

        WareTextField(
            value = state.email,
            onValueChange = { onEvent(SignupEvent.EmailChange(it)) },
            placeholder = "Email",
            contentDescription = "Enter email",
            message = state.emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )

        WareTextField(
            value = state.mobileNumber,
            onValueChange = { onEvent(SignupEvent.MobileNumberChange(it)) },
            placeholder = "Mobile Number",
            contentDescription = "Enter mobile number",
            message = state.mobileNumberError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Phone,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )

        WarePasswordTextField(
            value = state.password,
            onValueChange = { onEvent(SignupEvent.PasswordChange(it)) },
            placeholder = "Password",
            contentDescription = "Enter password",
            message = state.passwordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )

        WarePasswordTextField(
            value = state.matchPassword,
            onValueChange = { onEvent(SignupEvent.MatchPasswordChange(it)) },
            placeholder = "Confirm password",
            contentDescription = "Enter confirm password",
            message = state.matchPasswordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            backgroundColor = Color.White
        )


        WareDateTextField(
            value = state.birthDate,
            onValueChange = { onEvent(SignupEvent.BirthDateChange(it)) },
            placeholder = "BirthDate",
            contentDescription = "Enter birtDate",
            message = state.birthDateError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp)
                .clickable {

                },
            leadingIcon = Icons.Outlined.DateRange,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Enter)
            }),

            backgroundColor = Color.White
        )

        Button(onClick = {
                onEvent(SignupEvent.SignUp)
                navController.navigate("Store")

                Toast.makeText(context, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()

        }) {
            Text(text = "Create Account")

        }
    }
}



