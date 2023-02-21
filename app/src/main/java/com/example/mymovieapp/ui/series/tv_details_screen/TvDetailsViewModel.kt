package com.example.mymovieapp.ui.series.tv_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.app.models.movie.TvSeriesResponseUi
import com.example.mymovieapp.app.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TvDetailsViewModel constructor(
    private val tvId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Mapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>,
    private val mapTvSeriesResponseDomainToUi: Mapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: Mapper<SeriesUi, SeriesDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)


    private val tvFlow = MutableStateFlow(tvId)

    val movieFlow = tvFlow.flatMapLatest {
        movieRepository.getTvSeriesDetails(it)
    }.map(mapMovieDetailsDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val similarMoviesFlow = tvFlow.flatMapLatest {
        movieRepository.getTvSimilar(it)
    }.map(mapTvSeriesResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = tvFlow.flatMapLatest {
        movieRepository.getTvRecommendations(it)
    }.map(mapTvSeriesResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveTv(tv: SeriesUi) = viewModelScope.launch {
        storageRepository.tvSave(saveMapper.map(tv))
    }



    fun changeMovieId(movieId: Int) = tvFlow.tryEmit(movieId)
}
