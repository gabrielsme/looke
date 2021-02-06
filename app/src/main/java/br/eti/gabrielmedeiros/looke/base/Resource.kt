package br.eti.gabrielmedeiros.looke.base

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) : Resource<Nothing>()
    data class NetworkError(val error: ErrorResponse? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}