package com.example.mymovieapp.ui.adapters.click

interface RvClickListener<T> {
    fun onItemClick(item: T)
    fun onLongClick(item: T)
}