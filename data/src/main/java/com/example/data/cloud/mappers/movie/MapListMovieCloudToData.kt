package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper

class MapListMovieCloudToData (private val mapper: Mapper<MovieCloud, MovieData>) :
    Mapper<List<MovieCloud>, List<MovieData>> {
    override fun map(from: List<MovieCloud>) = from.map {
        mapper.map(it)
    }
}