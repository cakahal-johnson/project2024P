package com.example.kingselyweatherapp.Server

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private lateinit var retrofit: Retrofit

    private val client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build()

    fun getClient(): Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit

    }

}