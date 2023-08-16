package com.example.phonenew.data.local

import androidx.room.Entity

@Entity(tableName = "details_phones_table")
class PhoneDetailsEntity(
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)
