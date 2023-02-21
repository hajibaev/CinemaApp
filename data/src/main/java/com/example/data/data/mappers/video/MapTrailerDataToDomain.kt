package com.example.movieapp.data.mappers.video

import com.example.data.data.models.video.TrailerData
import com.example.domain.base.Mapper
import com.example.domain.models.video.TrailerDomain
import javax.inject.Inject

class MapTrailerDataToDomain @Inject constructor(): Mapper<TrailerData, TrailerDomain> {
    override fun map(from: TrailerData) = from.run {
        TrailerDomain(
            trailerName = trailerName,
            trailerKey = trailerKey,
            isOfficial = isOfficial
        )
    }
}
