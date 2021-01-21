package com.esys.mvvmboilerplate.data.model

sealed class ResultWrapper<out T> {
    data class Success<out T : Any?>(val data: T?) : ResultWrapper<T>()
    data class Error(val exception: Exception?) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}
