package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.MoviesResponseUi
import javax.inject.Inject

class MapMoviesDomainToUi @Inject constructor(private val mapper: Mapper<List<MovieDomain>, List<MovieUi>>) :
    Mapper<MoviesResponseDomain, MoviesResponseUi> {
    override fun map(from: MoviesResponseDomain) = from.run {
        MoviesResponseUi(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}
