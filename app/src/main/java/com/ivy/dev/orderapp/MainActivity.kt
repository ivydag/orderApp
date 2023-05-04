package com.ivy.dev.orderapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ivy.dev.orderapp.ui.theme.OrderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val sharedPreferences = context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)
                    val isUserRegister = sharedPreferences.getBoolean(REGISTER_USER, false)

                    val navController = rememberNavController()
                    MyNavigationComposable(navController)

                    if (isUserRegister) {
                        navController.navigate(Home.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    } else {
                        MyNavigationComposable(navController)
                    }
                }
            }
        }
    }

    companion object{
        const val REGISTER_USER = "REGISTER_USER"
    }
}