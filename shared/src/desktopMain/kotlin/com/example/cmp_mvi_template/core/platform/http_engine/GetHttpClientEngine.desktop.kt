package com.example.cmp_mvi_template.core.platform.http_engine

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getHttpClientEngine(): HttpClientEngine {
   return OkHttp.create()
}