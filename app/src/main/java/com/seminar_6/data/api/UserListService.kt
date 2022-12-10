package com.seminar_6.data.api

import com.seminar_6.data.model.UserListDto.ResponseUserListDto
import retrofit2.Response
import retrofit2.http.GET

interface UserListService {
    @GET("api/users?page=2")
    suspend fun getUserList(
    ): Response<ResponseUserListDto>
}


