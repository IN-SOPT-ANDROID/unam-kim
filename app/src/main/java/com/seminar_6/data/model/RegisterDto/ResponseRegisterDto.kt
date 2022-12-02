package com.seminar_6.data.model.RegisterDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRegisterDto(
    @SerialName("message")
    val message: String,
    @SerialName("newUser")
    val newUser: NewUser,
    @SerialName("status")
    val status: Int
) {
    @Serializable
    data class NewUser(
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("password")
        val password: String,
        @SerialName("profileImage")
        val profileImage: String?
    )
}

