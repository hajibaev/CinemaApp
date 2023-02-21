package com.example.data.data.repository

import android.content.Context
import com.example.domain.repository.LanguageRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(context: Context) : LanguageRepository {
    companion object {
        private const val SHARED_PREFS_NAME = "language"
        private const val LANGUAGE_KEY = "language"
        const val RU = "ru"
        const val US = "us"
    }

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveLanguage(): String {
        val language = if (getLanguage() == RU) US
        else RU
        sharedPreferences.edit().putString(LANGUAGE_KEY, language).apply()
        return language
    }

    override fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY, RU) ?: RU
    }
}