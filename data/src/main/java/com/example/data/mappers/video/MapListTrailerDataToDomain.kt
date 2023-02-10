package com.example.data.mappers.video

import com.example.data.models.video.TrailerData
import com.example.domain.Maps
import com.example.domain.models.video.TrailerDomain

class MapListTrailerDataToDomain(private val mapper:Maps<TrailerData, TrailerDomain>) :
    Maps<List<TrailerData>, List<TrailerDomain>> {
    override fun map(from: List<TrailerData>) = from.run {
        map {
            mapper.map(it)
        }
    }
}