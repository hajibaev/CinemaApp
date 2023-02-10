package com.example.domain.repository

interface LanguageRepository {
    fun saveLanguage(): String
    fun getLanguage(): String
}