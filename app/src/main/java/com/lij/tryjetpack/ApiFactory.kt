package com.lij.tryjetpack

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private val okHttpClient = OkHttpClient().newBuilder().build()
    fun retrofit(): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()

    val mainService = retrofit().create(ApiService::class.java)
}