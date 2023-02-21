package com.example.data.data.provide

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

abstract class ProvideInterceptorImpl(
    private val loggingInterceptor: HttpLoggingInterceptor.Level,
) :
    ProvideInterceptor {
    override fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = loggingInterceptor
    }

    override fun requestInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .cacheControl(CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build())
            .build()
        return@Interceptor chain.proceed(request)
    }

    class Debug : ProvideInterceptorImpl(loggingInterceptor = HttpLoggingInterceptor.Level.BODY)
    class Release : ProvideInterceptorImpl(loggingInterceptor = HttpLoggingInterceptor.Level.NONE)

}
