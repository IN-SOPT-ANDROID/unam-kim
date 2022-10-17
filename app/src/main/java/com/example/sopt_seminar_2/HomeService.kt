package com.example.sopt_seminar_2

import retrofit2.http.GET

interface HomeService {
    @GET("/v3/b58a7759-b72c-4bd1-835a-bc786f3d5ec1")
    fun getHomeList(): retrofit2.Call<HomeDto>
}