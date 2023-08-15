package com.example.phonenew.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phonenew.data.local.PhoneDao
import com.example.phonenew.data.local.PhoneEntity
import com.example.phonenew.data.remote.PhoneApi

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneEntity(): LiveData<List<PhoneEntity>> = phoneDao.getPhone()

    suspend fun getPhones() {
        val response = phoneApi.getData()
        if (response.isSuccessful) {
            val resp = response.body()
            resp?.let { phoneList ->
                val phoneEntityList = phoneList.map { phone ->
                    PhoneEntity(phone.id, phone.name, phone.price, phone.image)
                }
                phoneDao.insertPhone(phoneEntityList)
            }
        } else {
            Log.e("repositorio", response.errorBody().toString())
        }
    }
}