package com.example.domain

interface Maps<From, To> {
    fun map(from: From): To
}