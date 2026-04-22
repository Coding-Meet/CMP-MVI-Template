package com.example.cmp_mvi_template.core.domain

import kotlinx.serialization.json.Json

sealed interface ResultWrapper<out D, out E: Error> {
    data class Success<out D>(val data: D): ResultWrapper<D, Nothing>
    data class Error<out E: com.example.cmp_mvi_template.core.domain.Error>(val error: E):
        ResultWrapper<Nothing, E>
}

inline fun <T, E: Error, R> ResultWrapper<T, E>.map(map: (T) -> R): ResultWrapper<R, E> {
    return when(this) {
        is ResultWrapper.Error -> ResultWrapper.Error(error)
        is ResultWrapper.Success -> ResultWrapper.Success(map(data))
    }
}

inline fun <T, E: Error> ResultWrapper<T, E>.onSuccess(action: (T) -> Unit): ResultWrapper<T, E> {
    return when(this) {
        is ResultWrapper.Error -> this
        is ResultWrapper.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> ResultWrapper<T, E>.onError(action: (E) -> Unit): ResultWrapper<T, E> {
    return when(this) {
        is ResultWrapper.Error -> {
            action(error)
            this
        }
        is ResultWrapper.Success -> this
    }
}
inline fun <reified T, E : Error> ResultWrapper<T, E>.getOrNull(): T? {
    return when (this) {
        is ResultWrapper.Success -> {
            data
        }
        is ResultWrapper.Error -> null
    }
}
typealias EmptyResult<E> = ResultWrapper<Unit, E>