package com.example.cmp_mvi_template.feature.auth.data.remote.api

import com.example.cmp_mvi_template.feature.auth.data.remote.dto.LoginRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class AuthApiServiceImpl(
    private val httpClient: HttpClient
) : AuthApiService {

    override suspend fun login(request: LoginRequestDto): HttpResponse {
        return httpClient.post("auth/login") {
            setBody(request)
        }
    }

    override suspend fun getCurrentUser(token: String): HttpResponse {
        return httpClient.get("auth/me") {
            header("Authorization", "Bearer $token")
        }
    }
}
