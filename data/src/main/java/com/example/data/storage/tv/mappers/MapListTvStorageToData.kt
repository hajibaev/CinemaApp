package com.example.data.storage.tv.mappers

import com.example.data.data.models.movie.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.Mapper

class MapListTvStorageToData (private val mapper: Mapper<TvStorage, SeriesData>) :
    Mapper<List<TvStorage>, List<SeriesData>> {
    override fun map(from: List<TvStorage>) = from.map {
        mapper.map(it)
    }
}