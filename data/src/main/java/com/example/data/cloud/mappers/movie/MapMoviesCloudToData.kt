package com.example.data.cloud.mappers.movie
import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMoviesCloudToData @Inject constructor(private val mapper: Mapper<List<MovieCloud>, List<MovieData>>) :
    Mapper<MoviesResponseCloud, MoviesData> {
    override fun map(from: MoviesResponseCloud) = from.run {
        MoviesData(
            currectPage = currectPage,
            movies = mapper.map(movies),
            total_results = total_results,
            totalPages = totalPages
        )
    }
}