package com.example.mymovieapp.ui.tv_screen.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.models.movie.TvSeriesResponseUi
import com.example.mymovieapp.utils.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


private const val TV_ID_KEY = "tv_id_key"

class TvDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(TV_ID_KEY) private val tvId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Maps<TvSeriesDetailsDomain, TvSeriesDetailsUi>,
    private val mapTvSeriesResponseDomainToUi: Maps<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: Maps<SeriesUi, SeriesDomain>,
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == TvDetailsViewModel::class.java)
        return TvDetailsViewModel(
            tvId = tvId,
            storageRepository = storageRepository,
            mapMovieDetailsDomainToUi = mapMovieDetailsDomainToUi,
            movieRepository = movieRepository,
            mapTvSeriesResponseDomainToUi = mapTvSeriesResponseDomainToUi,
            saveMapper = saveMapper,
            resourceProvider = resourceProvider
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(TV_ID_KEY) tvId: Int,
        ): TvDetailsViewModelFactory
    }
}
