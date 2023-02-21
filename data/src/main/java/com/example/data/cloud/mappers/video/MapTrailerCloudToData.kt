package com.example.data.cloud.mappers.video

import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.data.models.video.TrailerData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapTrailerCloudToData @Inject constructor() : Mapper<TrailerCloud, TrailerData> {
    override fun map(from: TrailerCloud) = from.run {
        TrailerData(
            trailerName = trailerName,
            trailerKey = trailerKey,
            isOfficial = isOfficial
        )
    }
}