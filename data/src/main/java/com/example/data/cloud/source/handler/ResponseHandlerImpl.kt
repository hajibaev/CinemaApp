package com.example.data.cloud.source.handler

import com.example.domain.DataRequestState
import com.example.domain.Maps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ResponseHandlerImpl(
) : ResponseHandler {
    override suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T> {
        runCatching { withContext(Dispatchers.IO) { apiCall() } }.onSuccess { response ->
            if (response.isSuccessful) {
                val body =
                    withContext(Dispatchers.Default) { response.body() }
                body?.let {
                    return DataRequestState.Success(data = body)
                }
            }
        }
            .onFailure { exception -> return DataRequestState.Error(exception) }
        return DataRequestState.Error(
            error = java.lang.IllegalStateException()
        )
    }

    override suspend fun <T, K> safeApiMapperCall(
        mapper: Maps<T, K>,
        apiCall: suspend () -> Response<T>,
    ): DataRequestState<K> {
        runCatching { withContext(Dispatchers.IO) { apiCall() } }.onSuccess { response ->
            if (response.isSuccessful) {
                val body = withContext(Dispatchers.Default) { response.body() }
                body?.let {
                    return DataRequestState.Success(data = mapper.map(body))
                }
            }
        }
            .onFailure { exception -> return DataRequestState.Error(exception) }
        return DataRequestState.Error(
            error = java.lang.IllegalStateException()
        )
    }
}
