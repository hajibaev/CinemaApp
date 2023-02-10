package com.example.data.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory(): Converter.Factory
}