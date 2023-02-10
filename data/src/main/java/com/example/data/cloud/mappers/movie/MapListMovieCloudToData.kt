package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.models.movie.MovieData
import com.example.domain.Maps

class MapListMovieCloudToData(private val mapper: Maps<MovieCloud, MovieData>) :
    Maps<List<MovieCloud>, List<MovieData>> {
    override fun map(from: List<MovieCloud>) = from.map {
        mapper.map(it)
    }
}