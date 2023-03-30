package com.example.data.data.repository.tv

import com.example.data.cloud.source.tv.TvDataSource
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val source: TvDataSource,
    private val mapTvResponseDataToDomain: Mapper<TvSeriesResponseData, TvSeriesResponseDomain>,
    private val dispatchers: DispatchersProvider,
) : TvRepository {

    override fun fetchAllTrending(page: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllTrending(page = page).map(mapTvResponseDataToDomain::map)
                .flowOn(dispatchers.default())

    override fun fetchAllTopRated(page: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllTopRated(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllOnTheAir(page: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllOnTheAir(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllPopular(page: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllPopular(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllAiringToday(page: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllAiringToday(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllTvGenres(page: Int, genres: String): Flow<TvSeriesResponseDomain> =
        source.fetchAllTvGenres(page = page, genres = genres).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

}