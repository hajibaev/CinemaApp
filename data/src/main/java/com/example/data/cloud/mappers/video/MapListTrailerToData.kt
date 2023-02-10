package com.example.data.cloud.mappers.video

import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.models.video.TrailerData
import com.example.domain.Maps

class MapListTrailerToData(private val mapper: Maps<TrailerCloud, TrailerData>) :
    Maps<List<TrailerCloud>, List<TrailerData>> {
    override fun map(from: List<TrailerCloud>) = from.map {
        mapper.map(it)
    }
}

