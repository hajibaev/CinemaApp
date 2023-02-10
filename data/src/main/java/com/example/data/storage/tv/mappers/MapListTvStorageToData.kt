package com.example.data.storage.tv.mappers

import com.example.data.models.movie.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.Maps

class MapListTvStorageToData(private val mapper: Maps<TvStorage, SeriesData>) :
    Maps<List<TvStorage>, List<SeriesData>> {
    override fun map(from: List<TvStorage>) = from.map {
        mapper.map(it)
    }
}