package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.CastCloud
import com.example.data.models.movie.CastData
import com.example.domain.Maps

class MapListCastCloudToData(private val mapper: Maps<CastCloud, CastData>) :
    Maps<List<CastCloud>, List<CastData>> {
    override fun map(from: List<CastCloud>) = from.map {
        mapper.map(it)
    }
}