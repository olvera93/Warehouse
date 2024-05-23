package com.olvera.thewarehouse.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.olvera.thewarehouse.MainActivity
import com.olvera.thewarehouse.presentation.home.HomeView
import com.olvera.thewarehouse.presentation.onboarding.OnBoardingView
import com.olvera.thewarehouse.presentation.product.ProductView
import com.olvera.thewarehouse.presentation.product.ProductsViewModel
import com.olvera.thewarehouse.presentation.signup.SignUpView

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavManager(context: Context, onboardingCompleted: Boolean, productsViewModel: ProductsViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (onboardingCompleted) "Home" else "onBoarding"
    ) {
        composable("OnBoarding") {
            OnBoardingView(
                navController = navController,
                context = context,
                onboardingCompleted = onboardingCompleted
            )
        }

        composable("Home") {
            HomeView(navController = navController)
        }

        composable("SignUp") {
            SignUpView()
        }

        composable("ProductsView") {
            ProductView(productsViewModel = productsViewModel)
        }

    }

}