package br.eti.gabrielmedeiros.looke.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    val cause = throwable.cause
                    Resource.NetworkError(ErrorResponse(cause.toString()))
                }
                else -> {
                    Resource.GenericError(null, null)
                }
            }
        }
    }
}
