package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import javax.inject.Inject

class MapMoviesDataToDomain @Inject constructor(private val mapper: Mapper<List<MovieData>, List<MovieDomain>>) :
    Mapper<MoviesData, MoviesResponseDomain> {
    override fun map(from: MoviesData) = from.run {
        MoviesResponseDomain(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}