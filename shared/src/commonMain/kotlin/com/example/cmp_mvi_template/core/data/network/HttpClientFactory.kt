package com.example.cmp_mvi_template.core.data.network

import com.example.cmp_mvi_template.core.platform.http_engine.getHttpClientEngine
import com.example.cmp_mvi_template.core.utility.AppLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

private const val BASE_URL = "https://pokeapi.co/api/v2/"

object HttpClientFactory {
    fun create(): HttpClient {
        return HttpClient(
            engine = getHttpClientEngine()
        ) {
            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 20.seconds.inWholeMilliseconds
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            install(Logging) {
                level = if (AppLogger.isDebugBuild) LogLevel.ALL else LogLevel.NONE
                logger = if (AppLogger.isDebugBuild) object : Logger {
                    override fun log(message: String) {
                        AppLogger.d(tag = "KtorClient", null) {
                            message
                        }
                    }
                } else Logger.EMPTY
            }

        }
    }
}