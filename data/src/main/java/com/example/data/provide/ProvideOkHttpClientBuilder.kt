package com.example.data.provide

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun httpOkHttpClient(): OkHttpClient
}