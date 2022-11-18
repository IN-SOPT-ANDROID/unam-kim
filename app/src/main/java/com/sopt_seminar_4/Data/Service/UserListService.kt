package com.sopt_seminar_4.Data.Service

import com.sopt_seminar_4.Data.DTO.RegisterDTO.RequestRegisterDTO
import com.sopt_seminar_4.Data.DTO.RegisterDTO.ResponseRegisterDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserListService {
    @POST("api/users?page=2")
    suspend fun getUserList(
        @Body request: RequestRegisterDTO
    ): Response<ResponseRegisterDTO>
}


