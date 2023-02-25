package com.example.mymovieapp.ui.storage_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieStorageViewModel @Inject constructor(
    private val repository: MovieStorageRepository,
    private val mapMovieFromDomain: Mapper<List<MovieDomain>, List<MovieUi>>,
    private val mapSeriesDomainToUi: Mapper<List<SeriesDomain>, List<SeriesUi>>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val storageMovies = repository.getStorageMovies()
        .map(mapMovieFromDomain::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val tvStorage = repository.tvGetStorage()
        .map(mapSeriesDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteMovie(movieId: Int) = viewModelScope.launch {
        repository.delete(movieId = movieId)
    }

    fun deleteTV(tvId: Int) = viewModelScope.launch {
        repository.tvDelete(tvId = tvId)
    }

    fun launchTvDetails(seriesUi: SeriesUi) = navigate(
        StorageFragmentDirections.actionNavStorageToTvDetailsFragment(seriesUi)
    )

    fun launchMovieDetails(movieUi: MovieUi) = navigate(
        StorageFragmentDirections.actionStorageFragmentToMovieDetailsFragment(movieUi)
    )

}
