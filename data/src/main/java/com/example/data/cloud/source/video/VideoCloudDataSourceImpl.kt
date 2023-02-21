package com.example.data.cloud.source.video

import com.example.data.cloud.server.VideoApi
import com.example.data.cloud.models.video.VideoCloud
import com.example.data.data.models.video.VideoData
import com.example.domain.base.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoCloudDataSourceImpl @Inject constructor(
    private val api: VideoApi,
    private val mapper: Mapper<VideoCloud, VideoData>
) : VideoCloudDataSource {
    override suspend fun getAllTrailers(movieId: Int, language: String): Flow<VideoData> = flow {
        emit(api.getTrailer(movieId = movieId, language = language))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapper::map)
        .flowOn(Dispatchers.Default)
}