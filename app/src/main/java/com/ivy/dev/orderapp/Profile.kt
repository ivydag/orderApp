package com.ivy.dev.orderapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Profile (){
    Column(Modifier.fillMaxWidth()) {
        Text(text = "PROFILE")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Log out")
            
        }
    }
}