package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.models.movie.MovieUi

class MapListMovieDomainToUi(private val mapper: Maps<MovieDomain, MovieUi>) :
    Maps<List<MovieDomain>, List<MovieUi>> {
    override fun map(from: List<MovieDomain>) = from.run {
        map { movies ->
            mapper.map(movies)
        }
    }
}