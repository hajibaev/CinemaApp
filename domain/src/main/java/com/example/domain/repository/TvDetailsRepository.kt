package com.example.domain.repository

import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.models.person.CreditsResponseDomain
import kotlinx.coroutines.flow.Flow

interface TvDetailsRepository {

    fun fetchAllTvDetails(tvId: Int): Flow<TvSeriesDetailsDomain>

    fun fetchAllRecommendations(tvId: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllSimilar(tvId: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllCredits(tvId: Int): Flow<CreditsResponseDomain>

}