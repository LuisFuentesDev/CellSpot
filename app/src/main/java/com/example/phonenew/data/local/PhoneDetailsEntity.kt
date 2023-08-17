package com.example.phonenew.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details_phones_table")
class PhoneDetailsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)
