package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.person.CastCloud
import com.example.data.data.models.person.CastData
import com.example.domain.base.Mapper

class MapListCastCloudToData(private val mapper: Mapper<CastCloud, CastData>) :
    Mapper<List<CastCloud>, List<CastData>> {
    override fun map(from: List<CastCloud>) = from.map {
        mapper.map(it)
    }
}