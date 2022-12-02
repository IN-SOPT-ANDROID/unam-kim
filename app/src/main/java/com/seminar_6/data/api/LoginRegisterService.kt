package com.seminar_6.data.api

import com.seminar_6.data.model.LoginDto.RequestLoginDto
import com.seminar_6.data.model.LoginDto.ResponseLoginDto
import com.seminar_6.data.model.RegisterDto.RequestRegisterDto
import com.seminar_6.data.model.RegisterDto.ResponseRegisterDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRegisterService {
    @POST("api/user/signin")
    suspend fun login(
        @Body request: RequestLoginDto
    ): Response<ResponseLoginDto>

    @POST("api/user/signup")
    suspend fun register(
        @Body request: RequestRegisterDto
    ): Response<ResponseRegisterDto>
}