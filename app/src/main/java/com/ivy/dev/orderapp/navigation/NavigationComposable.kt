package com.ivy.dev.orderapp

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivy.dev.orderapp.data.MenuDao

@Composable
fun MyNavigationComposable(navController: NavHostController, menuDao: MenuDao) {
    val isUserConnected = false

    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
            Home(menuDao = menuDao, navController)
        }

        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }

        composable(Profile.route) {
           Profile(context = LocalContext.current)
        }

    }

}