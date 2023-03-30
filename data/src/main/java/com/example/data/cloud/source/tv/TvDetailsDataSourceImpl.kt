package com.example.data.cloud.source.tv

import com.example.data.cloud.models.movie.TvSeriesDetailsCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.service.TvDetailsService
import com.example.data.data.models.movie.TvSeriesDetailsData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.data.data.models.person.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvDetailsDataSourceImpl @Inject constructor(
    private val service: TvDetailsService,
    private val mapTvResponseCloudToData: Mapper<TvSeriesResponseCloud, TvSeriesResponseData>,
    private val mapTvDetailsCloudToData: Mapper<TvSeriesDetailsCloud, TvSeriesDetailsData>,
    private val mapCreditsResponseCloudToData: Mapper<CreditsResponseCloud, CreditsResponseData>,
    private val dispatchers: DispatchersProvider,
) : TvDetailsDataSource {

    override fun fetchAllTvCredits(tvId: Int): Flow<CreditsResponseData> = flow {
        emit(service.getTvCredits(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapCreditsResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllTvDetails(tvId: Int): Flow<TvSeriesDetailsData> = flow {
        emit(service.getTvDetails(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvDetailsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllSimilar(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getTvSimilar(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllRecommendations(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(service.getTvRecommendations(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

}