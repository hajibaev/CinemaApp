package com.example.data.cloud.source.tv

import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.service.TvService
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvDataSourceImpl @Inject constructor(
    private val service: TvService,
    private val mapTvResponseCloudToData: Mapper<TvSeriesResponseCloud, TvSeriesResponseData>,
    private val dispatchers: DispatchersProvider,
) : TvDataSource {

    override fun fetchAllTrending(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getTrendingTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllTopRated(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getTopRatedTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllOnTheAir(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getOnTheAirTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())


    override fun fetchAllPopular(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getPopularTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllAiringToday(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getAiringTodayTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())


    override fun fetchAllTvGenres(page: Int, genres: String): Flow<TvSeriesResponseData> = flow {
        emit(service.getTvGenres(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

}