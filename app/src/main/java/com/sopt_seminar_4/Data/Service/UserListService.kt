package com.sopt_seminar_4.Data.Service

import com.sopt_seminar_4.Data.DTO.RegisterDTO.RequestRegisterDTO
import com.sopt_seminar_4.Data.DTO.RegisterDTO.ResponseRegisterDTO
import com.sopt_seminar_4.Data.DTO.UserListDTO.ResponseUserListDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserListService {
    @GET("api/users?page=2")
    suspend fun getUserList(
    ): Response<ResponseUserListDTO>
}


