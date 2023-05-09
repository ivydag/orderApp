package com.ivy.dev.orderapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ivy.dev.orderapp.MainActivity.Companion.USER_EMAIL
import com.ivy.dev.orderapp.MainActivity.Companion.USER_LASTNAME
import com.ivy.dev.orderapp.MainActivity.Companion.USER_NAME

@Composable
fun Profile(context: Context) {
    val sharedPreferences = context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString(USER_NAME, "")
    val lastName = sharedPreferences.getString(USER_LASTNAME, "")
    val email = sharedPreferences.getString(USER_EMAIL, "")

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "PROFILE",
            Modifier.padding(16.dp)
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
            onClick = { sharedPreferences.edit().clear().apply() },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(text = "Log out")

        }
    }
}