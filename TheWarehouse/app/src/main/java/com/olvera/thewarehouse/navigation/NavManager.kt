package com.olvera.thewarehouse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olvera.thewarehouse.presentation.screens.home.HomeView
import com.olvera.thewarehouse.presentation.screens.signup.SignUpView
import com.olvera.thewarehouse.presentation.screens.signup.SignUpViewModel
import com.olvera.thewarehouse.presentation.screens.store.StoreView

@Composable
fun NavManager(
    signUpViewModel: SignUpViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("Home") {
            HomeView(navController)
        }

        composable("SignUp") {
            SignUpView(signUpViewModel, navController)
        }

        composable("Store") {
            StoreView()
        }

    }

}