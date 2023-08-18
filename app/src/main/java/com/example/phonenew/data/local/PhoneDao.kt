package com.example.phonenew.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phonenew.data.remote.DataPhoneDetails

@Dao
interface PhoneDao {
    //Lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneEntity: List<PhoneEntity>)

    @Query("SELECT * FROM phone_table order by id asc")
    fun getPhone(): LiveData<List<PhoneEntity>>

    //Detalle
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetails(phoneDetailsEntity: PhoneDetailsEntity)

    @Query("SELECT * FROM details_phones_table where id = :id")
    fun getPhoneDetails(id: Int): LiveData<PhoneDetailsEntity>
}