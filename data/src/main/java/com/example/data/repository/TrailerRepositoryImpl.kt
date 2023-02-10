package com.example.data.repository

import com.example.data.cloud.source.video.VideoCloudDataSource
import com.example.data.models.video.VideoData
import com.example.domain.Maps
import com.example.domain.models.video.VideosDomain
import com.example.domain.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TrailerRepositoryImpl(
    private val video: VideoCloudDataSource,
    private val videosMapper: Maps<VideoData, VideosDomain>
) : VideoRepository {

    override suspend fun getTrailers(movieId: Int, language: String): Flow<VideosDomain> =
        video.getAllTrailers(movieId = movieId, language = language)
            .map(videosMapper::map)
            .flowOn(Dispatchers.Default)

}