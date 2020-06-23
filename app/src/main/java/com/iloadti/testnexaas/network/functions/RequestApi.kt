package com.iloadti.testnexaas.network.functions


sealed class ResultApi<out R, out T> {
    data class Success<out R>(val value: R) : ResultApi<R, Nothing>()
    data class Failure<out T>(val value: T) : ResultApi<Nothing, T>()
}

inline fun <R, T, S> ResultApi<R, T>.flow(
    success: (R) -> S,
    failure: (T) -> S
): S = when (this) {
    is ResultApi.Success -> success(value)
    is ResultApi.Failure -> failure(value)
}

sealed class FailureError {
    object ErroNetwork : FailureError()
    data class ErrorException(val ex: Throwable) : FailureError()
}