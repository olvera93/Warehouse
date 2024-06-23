package com.olvera.thewarehouse.presentation.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.olvera.thewarehouse.R
import com.olvera.thewarehouse.components.WarePasswordTextField
import com.olvera.thewarehouse.components.WareTextField

@Composable
fun SignUpView(
    navController: NavController
) {

    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = stringResource(id = R.string.sign_up_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            WareTextField(
                value = "Username",
                onValueChange = { it },
                placeholder = "Username",
                contentDescription = "Enter your username",
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
                backgroundColor = Color.White,
            )
            Spacer(modifier = Modifier.weight(.2f))

            WareTextField(
                value = "Name",
                onValueChange = { it },
                placeholder = "Name",
                contentDescription = "Enter your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                leadingIcon = Icons.Outlined.Person,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                backgroundColor = Color.White,
            )
            Spacer(modifier = Modifier.weight(.2f))

            WareTextField(
                value = "Lastname",
                onValueChange = { it },
                placeholder = "Lastname",
                contentDescription = "Enter your Lastname",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                leadingIcon = Icons.Outlined.Person,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                backgroundColor = Color.White,
            )

            Spacer(modifier = Modifier.weight(.2f))

            WareTextField(
                value = "Email",
                onValueChange = { it },
                placeholder = "Email",
                contentDescription = "Enter your email",
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
                backgroundColor = Color.White,
            )

            Spacer(modifier = Modifier.weight(.2f))

            WarePasswordTextField(
                value = "state.matchPassword",
                onValueChange = { it },
                placeholder = "Confirm password",
                contentDescription = "Enter confirm password",
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

            Spacer(modifier = Modifier.weight(.2f))

            WareTextField(
                value = "Pno",
                onValueChange = { it },
                placeholder = "Mobile number",
                contentDescription = "Enter your mobile number",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp),
                leadingIcon = Icons.Outlined.Phone,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                backgroundColor = Color.White,
            )

            Spacer(modifier = Modifier.weight(.2f))

            CountrySelector()

            Spacer(modifier = Modifier.weight(.2f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 64.dp)
                    .padding(horizontal = 20.dp),
                onClick = {
                    navController.popBackStack()
                    navController.navigate("ProductsView")
                }) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate("Home")
                    }
                    .padding(top = 16.dp),
            ) {
                Text(
                    text = "Already have an account? ",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Red
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelector() {
    var expanded by remember { mutableStateOf(false) }
    val dropdownTitle = stringResource(id = R.string.dropdown_title)
    var selectedCountry by remember { mutableStateOf(dropdownTitle) }
    val countries = listOf(
        "México",
        "Estados Unidos",
        "Canadá",
        "España",
        "Argentina",
        "Colombia",
        "México",
        "Estados Unidos",
        "Canadá",
        "España",
        "Argentina",
        "Colombia"
    )
    val focusRequester = remember { FocusRequester() }


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            TextField(
                value = selectedCountry,
                onValueChange = { selectedCountry = it },
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                    .padding(horizontal = 20.dp)
                    .menuAnchor()
                    .focusRequester(focusRequester),
                label = { Text(text = stringResource(id = R.string.country_title)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = MaterialTheme.shapes.small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.tertiary.copy(
                        alpha = 0.5f
                    ),
                    focusedPlaceholderColor = MaterialTheme.colorScheme.tertiary.copy(
                        alpha = 0.5f
                    ),
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Transparent
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.exposedDropdownSize()
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country) },
                        onClick = {
                            selectedCountry = country
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun SignUpViewPreview() {
    val navController = rememberNavController()
    SignUpView(navController = navController)
}

