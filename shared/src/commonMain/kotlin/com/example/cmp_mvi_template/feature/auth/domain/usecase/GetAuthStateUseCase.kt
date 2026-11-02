package com.example.cmp_mvi_template.feature.auth.domain.usecase

import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import com.example.cmp_mvi_template.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetAuthStateUseCase(
    private val repository: AuthRepository
) {
    val authUser: Flow<AuthUser?> = repository.getAuthUser()
    val isLoggedIn: Flow<Boolean> = repository.isLoggedIn()
}

