package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.MoviesResponseUi

class MapMoviesDomainToUi(private val mapper: Maps<List<MovieDomain>, List<MovieUi>>) :
    Maps<MoviesResponseDomain, MoviesResponseUi> {
    override fun map(from: MoviesResponseDomain) = from.run {
        MoviesResponseUi(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}
