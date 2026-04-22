package com.example.cmp_mvi_template.core.data.network

import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ResultWrapper<T, DataError.Remote> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return ResultWrapper.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch(e: UnresolvedAddressException) {
        return ResultWrapper.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return ResultWrapper.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): ResultWrapper<T, DataError.Remote> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                ResultWrapper.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                ResultWrapper.Error(DataError.Remote.SERIALIZATION)
            }
        }
        408 -> ResultWrapper.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> ResultWrapper.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> ResultWrapper.Error(DataError.Remote.SERVER)
        else -> ResultWrapper.Error(DataError.Remote.UNKNOWN)
    }
}