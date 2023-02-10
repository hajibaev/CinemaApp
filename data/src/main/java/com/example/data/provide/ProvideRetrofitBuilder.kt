package com.example.data.provide

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {
    fun provideRetrofitBuilder(): Retrofit.Builder

}