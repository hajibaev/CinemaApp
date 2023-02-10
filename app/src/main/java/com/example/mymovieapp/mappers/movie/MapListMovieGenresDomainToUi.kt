package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain
import com.example.mymovieapp.models.movie.MovieGenresPresentation

class MapListMovieGenresDomainToUi(private val mapper: Maps<GenresDomain, MovieGenresPresentation>) :
    Maps<List<GenresDomain>, List<MovieGenresPresentation>> {
    override fun map(from: List<GenresDomain>) = from.map {
        mapper.map(it)
    }
}