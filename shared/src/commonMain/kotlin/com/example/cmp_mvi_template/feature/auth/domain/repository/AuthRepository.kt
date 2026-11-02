package com.example.cmp_mvi_template.feature.auth.domain.repository

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthUser(): Flow<AuthUser?>
    fun isLoggedIn(): Flow<Boolean>
    suspend fun login(username: String, password: String): ResultWrapper<AuthUser, DataError>
    suspend fun getCurrentUser(): ResultWrapper<AuthUser, DataError>
    suspend fun logout(): EmptyResult<DataError.Local>
}
