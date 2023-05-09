package com.ivy.dev.orderapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ivy.dev.orderapp.MainActivity.Companion.USER_EMAIL
import com.ivy.dev.orderapp.MainActivity.Companion.USER_LASTNAME
import com.ivy.dev.orderapp.MainActivity.Companion.USER_NAME
import com.ivy.dev.orderapp.ui.theme.green
import com.ivy.dev.orderapp.ui.theme.yellow

@Composable
fun Profile(context: Context, navController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString(USER_NAME, "")
    val lastName = sharedPreferences.getString(USER_LASTNAME, "")
    val email = sharedPreferences.getString(USER_EMAIL, "")

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = " Restaurant Logo",
                modifier = Modifier
                    .weight(3F)
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp, bottom = 5.dp),
                contentScale = ContentScale.Fit
            )
        }

        Text(
            text = "User Profile",
            fontSize= 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "user profile"
        )
        Text(
            text = "Name : $name",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.h4
        )

        Text(
            text = "Last name: $lastName",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Email:  $email",
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
            style = MaterialTheme.typography.subtitle1
        )

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate(Onboarding.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(text = "Log out")

        }
    }
}