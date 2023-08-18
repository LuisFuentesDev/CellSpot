package com.example.phonenew.data

import android.provider.ContactsContract.Data
import com.example.phonenew.data.local.PhoneDetailsEntity
import com.example.phonenew.data.local.PhoneEntity
import com.example.phonenew.data.remote.DataPhone
import com.example.phonenew.data.remote.DataPhoneDetails

fun DataPhone.transformToEntity(): PhoneEntity =
    PhoneEntity(this.id, this.name, this.price, this.image)

fun DataPhoneDetails.transformToDetailEntity(): PhoneDetailsEntity =
    PhoneDetailsEntity(
        this.id,
        this.name,
        this.price,
        this.image,
        this.description,
        this.lastPrice,
        this.credit
    )

