package com.example.movieapp.data.mappers.video

import com.example.data.models.video.TrailerData
import com.example.domain.Maps
import com.example.domain.models.video.TrailerDomain

class MapTrailerDataToDomain : Maps<TrailerData, TrailerDomain> {
    override fun map(from: TrailerData) = from.run {
        TrailerDomain(
            trailerName = trailerName,
            trailerKey = trailerKey,
            isOfficial = isOfficial
        )
    }
}
