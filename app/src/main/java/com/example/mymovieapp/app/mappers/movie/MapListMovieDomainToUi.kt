package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.app.models.movie.MovieUi

class MapListMovieDomainToUi (private val mapper: Mapper<MovieDomain, MovieUi>) :
    Mapper<List<MovieDomain>, List<MovieUi>> {
    override fun map(from: List<MovieDomain>) = from.run {
        map { movies ->
            mapper.map(movies)
        }
    }
}