package com.example.data.data.repository.tv

import com.example.data.cloud.source.tv.TvDetailsDataSource
import com.example.data.data.models.movie.TvSeriesDetailsData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.data.data.models.person.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.models.person.CreditsResponseDomain
import com.example.domain.repository.TvDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvDetailsRepositoryImpl @Inject constructor(
    private val source: TvDetailsDataSource,
    private val mapTvDetailsDataToDomain: Mapper<TvSeriesDetailsData, TvSeriesDetailsDomain>,
    private val mapCreditsResponseDataDomain: Mapper<CreditsResponseData, CreditsResponseDomain>,
    private val mapTvResponseDataToDomain: Mapper<TvSeriesResponseData, TvSeriesResponseDomain>,
    private val dispatchers: DispatchersProvider,
) : TvDetailsRepository {

    override fun fetchAllTvDetails(tvId: Int): Flow<TvSeriesDetailsDomain> =
        source.fetchAllTvDetails(tvId = tvId).map(mapTvDetailsDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllRecommendations(tvId: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllRecommendations(tvId = tvId).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllSimilar(tvId: Int): Flow<TvSeriesResponseDomain> =
        source.fetchAllSimilar(tvId = tvId).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchers.default())


    override fun fetchAllCredits(tvId: Int): Flow<CreditsResponseDomain> =
        source.fetchAllTvCredits(tvId = tvId).map(mapCreditsResponseDataDomain::map)
            .flowOn(dispatchers.default())

}