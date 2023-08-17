package com.example.phonenew.data

import com.example.phonenew.data.local.PhoneDetailsEntity
import com.example.phonenew.data.local.PhoneEntity

fun PhoneEntity.tranform(): PhoneEntity = PhoneEntity(this.id, this.name, this.price, this.image)
fun PhoneDetailsEntity.toDetailsEntity(): PhoneDetailsEntity = PhoneDetailsEntity(
    this.id,
    this.name,
    this.price,
    this.image,
    this.description,
    this.lastPrice,
    this.credit
)

