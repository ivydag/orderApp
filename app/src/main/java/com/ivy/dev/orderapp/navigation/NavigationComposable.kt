package com.ivy.dev.orderapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivy.dev.orderapp.Home
import com.ivy.dev.orderapp.Onboarding
import com.ivy.dev.orderapp.Profile
import com.ivy.dev.orderapp.data.MenuDao

@Composable
fun MyNavigationComposable(navController: NavHostController, menuDao: MenuDao) {

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
           Profile(context = LocalContext.current, navController)
        }

    }

}