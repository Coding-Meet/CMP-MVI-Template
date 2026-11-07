package com.example.cmp_mvi_template.feature.auth.data.repository

import com.example.cmp_mvi_template.core.data.network.safeCall
import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.core.domain.map
import com.example.cmp_mvi_template.feature.auth.data.datastore.AuthPreferences
import com.example.cmp_mvi_template.feature.auth.data.mapper.toDomain
import com.example.cmp_mvi_template.feature.auth.data.remote.api.AuthApiService
import com.example.cmp_mvi_template.feature.auth.data.remote.dto.LoginRequestDto
import com.example.cmp_mvi_template.feature.auth.data.remote.dto.LoginResponseDto
import com.example.cmp_mvi_template.feature.auth.data.remote.dto.UserDto
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser
import com.example.cmp_mvi_template.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val authPreferences: AuthPreferences
) : AuthRepository {

    override fun getAuthUser(): Flow<AuthUser?> {
        return authPreferences.authUser
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return authPreferences.isLoggedIn
    }

    override suspend fun login(
        username: String,
        password: String
    ): ResultWrapper<AuthUser, DataError> {
        val response = safeCall<LoginResponseDto> {
            apiService.login(LoginRequestDto(username = username, password = password))
        }

        return when (response) {
            is ResultWrapper.Success -> {
                val user = response.data.toDomain()
                authPreferences.saveAuthSession(response.data.accessToken, user)
                ResultWrapper.Success(user)
            }
            is ResultWrapper.Error -> ResultWrapper.Error(response.error)
        }
    }

    override suspend fun getCurrentUser(): ResultWrapper<AuthUser, DataError> {
        val token = authPreferences.accessToken.firstOrNull()
            ?: return ResultWrapper.Error(DataError.Remote.UNKNOWN)

        return safeCall<UserDto> {
            apiService.getCurrentUser(token)
        }.map { it.toDomain() }
    }

    override suspend fun logout(): EmptyResult<DataError.Local> {
        return try {
            authPreferences.clearAuthSession()
            ResultWrapper.Success(Unit)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }
}

