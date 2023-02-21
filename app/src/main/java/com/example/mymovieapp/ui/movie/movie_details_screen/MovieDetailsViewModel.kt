package com.example.mymovieapp.ui.movie.movie_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.CreditsResponseUi
import com.example.mymovieapp.app.models.movie.MovieDetailsUi
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.MoviesResponseUi
import com.example.mymovieapp.app.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel constructor(
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Mapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponseDomainToUi: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapCreditsResponseDomainToUi: Mapper<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieUiToDomain: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {
    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

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
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val actorsFlow = movieIdFlow.flatMapLatest {
        movieRepository.getActors(it)
    }.map(mapCreditsResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendationsMovies(it)
    }.map(mapMovieResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(mapMovieUiToDomain.map(movie))
    }

    fun changeMovieId(movieId: Int) = movieIdFlow.tryEmit(movieId)
}
