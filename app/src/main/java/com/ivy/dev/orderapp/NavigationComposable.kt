package com.ivy.dev.orderapp

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigationComposable(navController: NavHostController) {
    val isUserConnected = false

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
            Home()
        }

        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }

        composable(Profile.route) {
           Profile()
        }

    }

}