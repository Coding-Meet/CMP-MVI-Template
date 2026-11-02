package com.example.cmp_mvi_template.feature.auth.data.mapper

import com.example.cmp_mvi_template.feature.auth.data.remote.dto.LoginResponseDto
import com.example.cmp_mvi_template.feature.auth.data.remote.dto.UserDto
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser

fun LoginResponseDto.toDomain(): AuthUser {
    return AuthUser(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image
    )
}

fun UserDto.toDomain(): AuthUser {
    return AuthUser(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        image = image
    )
}
