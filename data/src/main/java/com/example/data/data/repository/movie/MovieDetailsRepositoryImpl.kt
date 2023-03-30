package com.example.data.data.repository.movie

import com.example.data.cloud.source.movie.MovieDetailsDataSource
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.data.data.models.person.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.models.person.CreditsResponseDomain
import com.example.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val source: MovieDetailsDataSource,
    private val mapCreditsResponseDataDomain: Mapper<CreditsResponseData, CreditsResponseDomain>,
    private val mapMovieDetailsToDomain: Mapper<MovieDetailsData, MovieDetailsDomain>,
    private val mapMoviesDataToDomain: Mapper<MoviesData, MoviesResponseDomain>,
    private val dispatchers: DispatchersProvider,
) : MovieDetailsRepository {

    override fun fetchAllMovieDetails(movieId: Int): Flow<MovieDetailsDomain> =
        source.fetchAllMovieDetails(movieId = movieId).map(mapMovieDetailsToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllRecommendations(movieId: Int): Flow<MoviesResponseDomain> =
        source.fetchRecommendationsMovies(movieId = movieId).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllSimilar(movieId: Int): Flow<MoviesResponseDomain> =
        source.fetchSimilarMovies(movieId = movieId).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllCredits(movieId: Int): Flow<CreditsResponseDomain> =
        source.fetchAllCredits(movieId = movieId).map(mapCreditsResponseDataDomain::map)
            .flowOn(dispatchers.default())

}