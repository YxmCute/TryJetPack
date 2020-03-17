package com.lij.tryjetpack

import retrofit2.http.GET

interface ApiService {
    @GET("banner/json")
    suspend fun getMainBanner(): BaseResponse<List<MainBanner>>
}