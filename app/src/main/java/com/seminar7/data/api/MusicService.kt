package com.seminar7.data.api

import com.seminar7.data.model.ResponseCreateMusicDto
import com.seminar7.data.model.ResponseGetMusicDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface MusicService {
    @GET("music/list")
    suspend fun getMusicList(
    ): Response<ResponseGetMusicDto>

    @Multipart
    @POST("music")
    suspend fun uploadProfileImage(
        @PartMap body: HashMap<String, ReqeustBody>,
        @Part("image") image: MultipartBody.Part
    ): Response<ResponseCreateMusicDto>
}