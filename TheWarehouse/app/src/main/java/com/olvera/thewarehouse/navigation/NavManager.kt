package com.olvera.thewarehouse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.olvera.thewarehouse.presentation.screens.home.HomeView

@Composable
fun NavManager() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("Home") {
            HomeView()
        }
    }

}