package com.sopt_seminar_4.Data.Service

import com.sopt_seminar_4.Data.DTO.RegisterDTO.RequestRegisterDTO
import com.sopt_seminar_4.Data.DTO.RegisterDTO.ResponseRegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("api/user/signup")
    suspend fun register(
        @Body request: RequestRegisterDTO
    ): Response<ResponseRegisterDTO>
}