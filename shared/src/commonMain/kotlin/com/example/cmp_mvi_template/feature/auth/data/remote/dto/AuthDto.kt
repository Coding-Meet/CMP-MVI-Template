package com.example.cmp_mvi_template.feature.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val username: String,
    val password: String,
    val expiresInMins: Int = 60
)

@Serializable
data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class UserDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String
)

