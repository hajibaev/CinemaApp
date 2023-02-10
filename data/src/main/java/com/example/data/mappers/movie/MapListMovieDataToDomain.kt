package com.example.data.mappers.movie

import com.example.data.models.movie.MovieData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain

class MapListMovieDataToDomain(private val mapper: Maps<MovieData, MovieDomain>) :
    Maps<List<MovieData>, List<MovieDomain>> {
    override fun map(from: List<MovieData>) = from.map {
        mapper.map(it)
    }
}