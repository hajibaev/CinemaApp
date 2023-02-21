package com.example.mymovieapp.ui

import android.content.Context
import androidx.annotation.StringRes

sealed class ResourceString {

    abstract fun format(context: Context): String


}

data class IdResourceString(@StringRes val id: Int) : ResourceString() {
    override fun format(context: Context): String = context.getString(id)
}