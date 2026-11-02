package com.example.cmp_mvi_template.feature.auth.data.remote.api

import com.example.cmp_mvi_template.feature.auth.data.remote.dto.LoginRequestDto
import io.ktor.client.statement.HttpResponse

interface AuthApiService {
    suspend fun login(request: LoginRequestDto): HttpResponse
    suspend fun getCurrentUser(token: String): HttpResponse
}
