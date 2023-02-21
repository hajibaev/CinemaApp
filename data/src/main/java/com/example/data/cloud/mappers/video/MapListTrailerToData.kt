package com.example.data.cloud.mappers.video

import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.data.models.video.TrailerData
import com.example.domain.base.Mapper

class MapListTrailerToData (private val mapper: Mapper<TrailerCloud, TrailerData>) :
    Mapper<List<TrailerCloud>, List<TrailerData>> {
    override fun map(from: List<TrailerCloud>) = from.map {
        mapper.map(it)
    }
}

