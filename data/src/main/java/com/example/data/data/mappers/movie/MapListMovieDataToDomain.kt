package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain

class MapListMovieDataToDomain (private val mapper: Mapper<MovieData, MovieDomain>) :
    Mapper<List<MovieData>, List<MovieDomain>> {
    override fun map(from: List<MovieData>) = from.map {
        mapper.map(it)
    }
}