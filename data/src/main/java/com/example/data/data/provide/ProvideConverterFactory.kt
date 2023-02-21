package com.example.data.data.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory(): Converter.Factory
}