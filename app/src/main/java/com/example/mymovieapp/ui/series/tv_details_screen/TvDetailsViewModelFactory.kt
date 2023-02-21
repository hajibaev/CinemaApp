package com.example.mymovieapp.ui.series.tv_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.app.models.movie.TvSeriesResponseUi
import com.example.mymovieapp.app.utils.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


private const val TV_ID_KEY = "tv_id_key"

class TvDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(TV_ID_KEY) private val tvId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Mapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>,
    private val mapTvSeriesResponseDomainToUi: Mapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: Mapper<SeriesUi, SeriesDomain>,
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
