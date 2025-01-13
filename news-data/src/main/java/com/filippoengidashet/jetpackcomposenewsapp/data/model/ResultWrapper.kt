package com.filippoengidashet.jetpackcomposenewsapp.data.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val error: Throwable) : ResultWrapper<Nothing>()
}
