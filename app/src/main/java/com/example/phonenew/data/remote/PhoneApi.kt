package com.example.phonenew.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PhoneApi {
    @GET("products")
    suspend fun getData(): Response<List<DataPhone>>

    @GET("details")
    suspend fun getDetailsData(id:String): Response<List<PhoneDetails>>
}