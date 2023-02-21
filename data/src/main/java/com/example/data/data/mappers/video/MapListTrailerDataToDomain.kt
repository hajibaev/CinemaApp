package com.example.data.data.mappers.video

import com.example.data.data.models.video.TrailerData
import com.example.domain.base.Mapper
import com.example.domain.models.video.TrailerDomain

class MapListTrailerDataToDomain(private val mapper: Mapper<TrailerData, TrailerDomain>) :
    Mapper<List<TrailerData>, List<TrailerDomain>> {
    override fun map(from: List<TrailerData>) = from.run {
        map {
            mapper.map(it)
        }
    }
}