package com.example.data.data.provide

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}