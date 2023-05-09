package com.ivy.dev.orderapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ivy.dev.orderapp.MainActivity.Companion.REGISTER_USER
import com.ivy.dev.orderapp.ui.theme.green
import com.ivy.dev.orderapp.ui.theme.yellow


@Composable
fun Onboarding(navController: NavHostController) {
    Box(modifier = Modifier.background(Color.White)) {

        var isValid = false
        val context = LocalContext.current

        val sharedPreferences = context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)


        var userName by remember {
            mutableStateOf("")
        }

        var lastName by remember {
            mutableStateOf("")
        }

        var email by remember {
            mutableStateOf("")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Brand logo",
                modifier = Modifier
                    .padding(5.dp)
            )

            Text(
                text = "Lets get to know you",
                modifier = Modifier
                    .background(green)
                    .padding(20.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = yellow,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Text(
                textAlign = TextAlign.Start,
                text = "Personal Information",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 20.dp, start = 5.dp, bottom = 20.dp),

                )

            TextField(
                value = userName,
                label = { Text("Enter your name") },
                onValueChange = { newValue -> userName = newValue},
                modifier = Modifier.padding(5.dp)
            )
            TextField(
                value = lastName,
                label = { Text("Enter your last name") },
                onValueChange = { newValue -> lastName = newValue},
                modifier = Modifier.padding(5.dp)
            )
            TextField(
                value = email,
                label = { Text("Enter your email") },
                onValueChange = { newValue -> email = newValue },
                modifier = Modifier.padding(5.dp)
            )

            Button(onClick = {
                isValid = when {
                    userName.isBlank() -> false
                    lastName.isBlank() -> false
                    email.isBlank() -> false
                    else -> true
                }
                Toast.makeText(context, isValid.toString(), Toast.LENGTH_SHORT).show()
                if (isValid){
                    navController.navigate(Home.route) {
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }

                    sharedPreferences.edit().putBoolean(REGISTER_USER, true).apply()
                }

            }, modifier = Modifier.padding(5.dp)) {
                Text(text = "Register")

            }
        }

    }
}