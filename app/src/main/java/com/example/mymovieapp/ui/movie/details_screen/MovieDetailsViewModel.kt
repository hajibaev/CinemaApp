package com.example.mymovieapp.ui.movie.details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.CreditsResponseUi
import com.example.mymovieapp.models.movie.MovieDetailsUi
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.MoviesResponseUi
import com.example.mymovieapp.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel constructor(
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Maps<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponseDomainToUi: Maps<MoviesResponseDomain, MoviesResponseUi>,
    private val mapCreditsResponseDomainToUi: Maps<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieUiToDomain: Maps<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {
    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)

    val similarMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val movieFlow = movieIdFlow.flatMapLatest {
        movieRepository.getDetails(it)
    }.map(mapMovieDetailsDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val actorsFlow = movieIdFlow.flatMapLatest {
        movieRepository.getActors(it)
    }.map(mapCreditsResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendationsMovies(it)
    }.map(mapMovieResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(mapMovieUiToDomain.map(movie))
    }

    fun changeMovieId(movieId: Int) = movieIdFlow.tryEmit(movieId)
}
