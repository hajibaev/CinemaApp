package com.example.data.cloud.source.movie

import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.service.SearchService
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val service: SearchService,
    private val mapListMovieCloudToData: Mapper<MoviesResponseCloud, MoviesData>,
    private val dispatchers: DispatchersProvider,
) : SearchDataSource {

    override fun fetchAllSearch(query: String): Flow<MoviesData> = flow {
        emit(service.getSearchMovies(query = query))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

}