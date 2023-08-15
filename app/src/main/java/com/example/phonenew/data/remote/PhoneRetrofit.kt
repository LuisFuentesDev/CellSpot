package com.example.phonenew.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneRetrofit {
    companion object {
        private const val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getPhoneRetrofit(): PhoneApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(PhoneApi::class.java)
        }
    }
}