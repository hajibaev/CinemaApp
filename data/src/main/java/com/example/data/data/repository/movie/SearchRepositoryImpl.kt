package com.example.data.data.repository.movie

import com.example.data.cloud.source.movie.SearchDataSource
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val source: SearchDataSource,
    private val mapMoviesDataToDomain: Mapper<MoviesData, MoviesResponseDomain>,
    private val dispatchers: DispatchersProvider,
) : SearchRepository {

    override fun fetchAllSearch(query: String): Flow<MoviesResponseDomain> =
        source.fetchAllSearch(query = query).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

}