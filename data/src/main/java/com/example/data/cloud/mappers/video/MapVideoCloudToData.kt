package com.example.data.cloud.mappers.video

import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.cloud.models.video.VideoCloud
import com.example.data.models.video.TrailerData
import com.example.data.models.video.VideoData
import com.example.domain.Maps

class MapVideoCloudToData(private val videoMapper: Maps<List<TrailerCloud>, List<TrailerData>>) :
    Maps<VideoCloud, VideoData> {
    override fun map(from: VideoCloud) = from.run {
        VideoData(
            id = id,
            trailers = videoMapper.map(trailers)
        )
    }
}