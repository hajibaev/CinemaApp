package com.example.data.cloud.source.handler

import com.example.domain.DataRequestState
import com.example.domain.Maps
import retrofit2.Response

interface ResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T>
    suspend fun <T, K> safeApiMapperCall(mapper: Maps<T, K>, apiCall: suspend () -> Response<T>, ): DataRequestState<K>
}