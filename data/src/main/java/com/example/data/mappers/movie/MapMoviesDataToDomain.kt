package com.example.data.mappers.movie

import com.example.data.models.movie.MovieData
import com.example.data.models.movie.MoviesData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain

class MapMoviesDataToDomain(private val mapper:Maps<List<MovieData>, List<MovieDomain>> ) :
    Maps<MoviesData, MoviesResponseDomain> {
    override fun map(from: MoviesData) = from.run {
        MoviesResponseDomain(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}