package com.example.phonenew.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.phonenew.data.local.PhoneDao
import com.example.phonenew.data.local.PhoneDetailsEntity
import com.example.phonenew.data.local.PhoneEntity
import com.example.phonenew.data.remote.PhoneApi

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhoneEntity(): LiveData<List<PhoneEntity>> = phoneDao.getPhone()

    fun getPhoneDetailsEntity(id: Int): LiveData<PhoneDetailsEntity> =
        phoneDao.getPhoneDetails(id)

    suspend fun getPhones() {
        try {
            val response = phoneApi.getData()
            if (response.isSuccessful) {
                val phoneList = response.body()
                phoneList?.let {
                    val cellPhoneEntity = it.map { it.transformToEntity() }
                    phoneDao.insertPhone(cellPhoneEntity)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error getting phones: ${e.message}")
        }
    }

    suspend fun getPhoneDetails(id: Int) {
        try {
            val response = phoneApi.getDetailsData(id)
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {phoneDetails->
                    val phoneDetailsEntity = phoneDetails.transformToDetailEntity()
                    phoneDao.insertPhoneDetails(phoneDetailsEntity)
                }
            } else {
                Log.e("Repository", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error getting phones: ${e.message}")
        }
    }
}
