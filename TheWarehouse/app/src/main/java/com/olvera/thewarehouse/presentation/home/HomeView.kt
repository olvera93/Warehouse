package com.olvera.thewarehouse.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.olvera.thewarehouse.R
import com.olvera.thewarehouse.components.WarePasswordTextField
import com.olvera.thewarehouse.components.WareTextField

@Composable
fun HomeView(
    navController: NavController
) {

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.TopCenter
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Loader()
            Text(
                text = "Welcome to The Warehouse",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
            )

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

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 64.dp)
                    .padding(horizontal = 20.dp),
                onClick = {
                    navController.popBackStack()
                    navController.navigate("ProductsView")
                }) {
                Text(text = "Login")
            }

            Text(text = "Forgot your password?")

            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate("SignUp")
                    }
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "If you don't have an account, ",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Text(
                    text = "create one",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Red
                )
            }
        }
    }
}


@Composable
fun Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ecommerce))

    val progress by animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .height(330.dp)
            .width(300.dp)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeViewPreview() {
    val navController = rememberNavController()
    HomeView(navController = navController)
}