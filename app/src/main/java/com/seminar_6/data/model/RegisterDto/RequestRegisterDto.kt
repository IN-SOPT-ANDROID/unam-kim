package com.seminar_6.data.model.RegisterDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRegisterDto(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String
)