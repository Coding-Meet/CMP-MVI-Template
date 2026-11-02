package com.example.cmp_mvi_template.feature.auth.domain.entity

data class AuthUser(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String
) {
    val fullName: String
        get() = "$firstName $lastName"
}
