package com.example.domain.repository

import com.example.domain.models.video.VideosDomain
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getTrailers(movieId: Int, language: String): Flow<VideosDomain>
}