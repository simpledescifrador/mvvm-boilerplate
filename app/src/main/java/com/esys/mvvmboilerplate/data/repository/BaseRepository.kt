package com.esys.mvvmboilerplate.data.repository

import com.esys.mvvmboilerplate.data.model.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T : Any> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> ResultWrapper<T>,
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                Timber.tag("SafeApiCall").d("Called: $apiCall")
                apiCall.invoke()
            } catch (e: Exception) {
                Timber.tag("SafeApiCallError").e(e)
                ResultWrapper.NetworkError
            }
        }
    }

    protected suspend fun <T> retryIO(
        times: Int = Int.MAX_VALUE,
        initialDelay: Long = 100, // 0.1 second
        maxDelay: Long = 1000, // 1 second
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                Timber.tag("RetryIO").d("Retrying Running...")
                return block()
            } catch (e: IOException) {
                Timber.tag("RetryIOError").e(e)
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }
}
