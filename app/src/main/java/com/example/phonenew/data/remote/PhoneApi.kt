package com.example.phonenew.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {
    @GET("products")
    suspend fun getData(): Response<List<DataPhone>>

    @GET("details{id}")
    suspend fun getDetailsData(@Path("id")id:Int): Response<DataPhoneDetails>
}