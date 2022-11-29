package com.sopt_seminar_4.Data.Service

import com.sopt_seminar_4.Data.DTO.LoginDTO.RequestLoginDTO
import com.sopt_seminar_4.Data.DTO.LoginDTO.ResponseLoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/user/signin")
    suspend fun login(
        @Body request: RequestLoginDTO
    ): Response<ResponseLoginDTO>
}