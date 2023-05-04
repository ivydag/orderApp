package com.ivy.dev.orderapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun Home (){
    Column(androidx.compose.ui.Modifier.fillMaxWidth().background(Color.Magenta)) {
        Text(text = "HOME")
    }
}