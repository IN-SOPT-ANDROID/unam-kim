package com.seminar7.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiMusic {
    private const val BASE_URL = "http://3.36.161.194:3000/"
    private var retrofit: Retrofit? = null

    @OptIn(ExperimentalSerializationApi::class)
    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()
        }
        return retrofit!!
    }

    inline fun <reified T> create(): T = getRetrofit().create<T>(T::class.java)

    object ServicePool {
        val musicService = create<MusicService>()
    }
}