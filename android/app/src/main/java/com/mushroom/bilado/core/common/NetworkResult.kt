package com.mushroom.bilado.core.common


sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>

    data class Failure(
        val error: NetworkError,
        val cause: Throwable? = null
    ) : NetworkResult<Nothing>
}

sealed interface NetworkError {
    data object NetworkUnavailable : NetworkError
    data object Timeout : NetworkError
    data class Http(val code: Int, val message: String? = null) : NetworkError
    data object Serialization : NetworkError
    data object Unknown : NetworkError
}

inline fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> = when (this) {
    is NetworkResult.Success -> NetworkResult.Success(transform(data))
    is NetworkResult.Failure -> this
}
