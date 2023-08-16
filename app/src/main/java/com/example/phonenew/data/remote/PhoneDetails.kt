package com.example.phonenew.data.remote

data class PhoneDetails(
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)
