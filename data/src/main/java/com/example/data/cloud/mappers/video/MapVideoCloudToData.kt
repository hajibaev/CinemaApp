package com.example.data.cloud.mappers.video

import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.cloud.models.video.VideoCloud
import com.example.data.data.models.video.TrailerData
import com.example.data.data.models.video.VideoData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapVideoCloudToData @Inject constructor(private val videoMapper: Mapper<List<TrailerCloud>, List<TrailerData>>) :
    Mapper<VideoCloud, VideoData> {
    override fun map(from: VideoCloud) = from.run {
        VideoData(
            id = id,
            trailers = videoMapper.map(trailers)
        )
    }
}