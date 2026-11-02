package com.example.cmp_mvi_template.feature.auth.domain.usecase

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import com.example.cmp_mvi_template.feature.auth.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): ResultWrapper<AuthUser, DataError> {
        return repository.login(username.trim(), password)
    }
}

