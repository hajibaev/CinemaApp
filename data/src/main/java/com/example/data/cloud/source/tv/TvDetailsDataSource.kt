package com.example.data.cloud.source.tv

import com.example.data.data.models.movie.TvSeriesDetailsData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.data.data.models.person.CreditsResponseData
import kotlinx.coroutines.flow.Flow

interface TvDetailsDataSource {

    fun fetchAllTvCredits(tvId: Int): Flow<CreditsResponseData>

    fun fetchAllTvDetails(tvId: Int): Flow<TvSeriesDetailsData>

    fun fetchAllSimilar(tvId: Int): Flow<TvSeriesResponseData>

    fun fetchAllRecommendations(tvId: Int): Flow<TvSeriesResponseData>

}