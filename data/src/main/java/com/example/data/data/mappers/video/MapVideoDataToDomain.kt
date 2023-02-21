package com.example.movieapp.data.mappers.video

import com.example.data.data.models.video.TrailerData
import com.example.data.data.models.video.VideoData
import com.example.domain.base.Mapper
import com.example.domain.models.video.TrailerDomain
import com.example.domain.models.video.VideosDomain
import javax.inject.Inject

class MapVideoDataToDomain @Inject constructor(private val mapper: Mapper<List<TrailerData>, List<TrailerDomain>>) :
    Mapper<VideoData, VideosDomain> {
    override fun map(from: VideoData) = from.run {
        VideosDomain(
            id = id,
            trailers = mapper.map(trailers)
        )
    }
}