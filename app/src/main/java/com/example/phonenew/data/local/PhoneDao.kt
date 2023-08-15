package com.example.phonenew.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: PhoneEntity)

    @Query("SELECT * FROM phone_table Order by id asc")
    fun getPhone(): LiveData<List<PhoneEntity>>
}