package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain
import com.example.mymovieapp.models.movie.MovieGenresPresentation

class MapGenresDomainToUi : Maps<GenresDomain, MovieGenresPresentation> {
    override fun map(from: GenresDomain) = from.run {
        MovieGenresPresentation(
            id = id,
            name = name
        )
    }
}