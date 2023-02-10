package com.example.mymovieapp.utils.event

class Event<T>  (
    private val value: T,
) {
    private var handled: Boolean = false

    fun getValue(): T? {
        if (handled) return null
        handled = true
        return value
    }
}