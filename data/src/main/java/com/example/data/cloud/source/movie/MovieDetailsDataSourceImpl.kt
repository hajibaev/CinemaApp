package com.example.data.cloud.source.movie

import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.service.MovieDetailsService
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.person.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailsDataSourceImpl @Inject constructor(
    private val service: MovieDetailsService,
    private val mapListMovieCloudToData: Mapper<MoviesResponseCloud, MoviesData>,
    private val mapDetailsCloudToData: Mapper<MovieDetailsCloud, MovieDetailsData>,
    private val mapCreditsResponseCloudToData: Mapper<CreditsResponseCloud, CreditsResponseData>,
    private val dispatchers: DispatchersProvider,
) : MovieDetailsDataSource {

    override fun fetchAllMovieDetails(movieId: Int): Flow<MovieDetailsData> = flow {
        emit(service.getDetailsMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapDetailsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchSimilarMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(service.getSimilarMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchRecommendationsMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(service.getRecommendationsMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    override fun fetchAllCredits(movieId: Int): Flow<CreditsResponseData> = flow {
        emit(service.getMovieCredits(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapCreditsResponseCloudToData::map)
        .flowOn(dispatchers.default())

}