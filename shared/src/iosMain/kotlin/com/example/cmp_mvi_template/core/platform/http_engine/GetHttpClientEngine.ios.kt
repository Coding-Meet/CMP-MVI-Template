package com.example.cmp_mvi_template.core.platform.http_engine

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getHttpClientEngine(): HttpClientEngine {
   return Darwin.create()
}