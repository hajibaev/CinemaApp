package com.example.data.provide

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}