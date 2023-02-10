package com.example.data.cloud.source.video

import com.example.data.models.video.VideoData
import kotlinx.coroutines.flow.Flow

interface VideoCloudDataSource {
    suspend fun getAllTrailers(movieId: Int, language: String): Flow<VideoData>
}