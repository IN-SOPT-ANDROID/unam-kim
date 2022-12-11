package com.seminar7.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMusicDto(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("statusCode")
    val statusCode: Int,
    @SerialName("success")
    val success: Boolean
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("singer")
        val singer: String,
        @SerialName("title")
        val title: String
    )
}