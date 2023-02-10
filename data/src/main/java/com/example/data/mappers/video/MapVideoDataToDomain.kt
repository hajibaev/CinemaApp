package com.example.movieapp.data.mappers.video

import com.example.data.models.video.TrailerData
import com.example.data.models.video.VideoData
import com.example.domain.Maps
import com.example.domain.models.video.TrailerDomain
import com.example.domain.models.video.VideosDomain

class MapVideoDataToDomain(private val mapper: Maps<List<TrailerData>, List<TrailerDomain>>) :
    Maps<VideoData, VideosDomain> {
    override fun map(from: VideoData) = from.run {
        VideosDomain(
            id = id,
            trailers = mapper.map(trailers)
        )
    }
}