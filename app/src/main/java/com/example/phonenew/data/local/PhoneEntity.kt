package com.example.phonenew.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_table")
data class PhoneEntity(@PrimaryKey val id:Int, val name:String,val price:Int, val image:String)
