package com.example.data.cloud.mappers.movie
import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.models.movie.MovieData
import com.example.data.models.movie.MoviesData
import com.example.domain.Maps

class MapMoviesCloudToData(private val mapper: Maps<List<MovieCloud>, List<MovieData>>) :
    Maps<MoviesResponseCloud, MoviesData> {
    override fun map(from: MoviesResponseCloud) = from.run {
        MoviesData(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}