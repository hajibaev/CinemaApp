package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.GenresCloud
import com.example.data.models.movie.GenresData
import com.example.domain.Maps

class MapListGenresCloudToData(private val mapper: Maps<GenresCloud, GenresData>) :
    Maps<List<GenresCloud>, List<GenresData>> {
    override fun map(from: List<GenresCloud>) = from.map {
        mapper.map(it)
    }
}