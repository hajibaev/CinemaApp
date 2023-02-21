package com.example.data.cloud.source.handler

import com.example.domain.base.Mapper
import com.example.domain.state.DataRequestState
import retrofit2.Response

interface ResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T>
    suspend fun <T, K> safeApiMapperCall(mapper: Mapper<T, K>, apiCall: suspend () -> Response<T>, ): DataRequestState<K>
}