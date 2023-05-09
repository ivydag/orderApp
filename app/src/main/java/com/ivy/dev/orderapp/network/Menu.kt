package com.ivy.dev.orderapp.network

data class Menu(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
)