package com.olvera.thewarehouse.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.AsyncUpdates
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.olvera.thewarehouse.R
import com.olvera.thewarehouse.presentation.components.CircleButton

@Composable
fun HomeView(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
            )
            Loader()
            Spacer(modifier = Modifier.height(50.dp))

            CircleButton(
                icon = Icons.Rounded.Email,
                text = stringResource(id = R.string.register_user_email)
            ) {
                navController.navigate("SignUp")
            }
            Spacer(modifier = Modifier.height(20.dp))
            CircleButton(
                icon = Icons.Rounded.Person,
                text = stringResource(id = R.string.register_google)
            ) {}

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    text = stringResource(id = R.string.have_an_account)
                )
                Text(
                    text = stringResource(id = R.string.sing_in),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ecommerce))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .height(330.dp)
            .width(300.dp),
        asyncUpdates = AsyncUpdates.AUTOMATIC
    )
}


/*@Composable
@Preview
fun HomeViewPreview() {
    HomeView(
        navController =
    )
}*/