package com.example.mymovieapp.ui.movie.movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.MoviesResponseUi
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.ui.see_all_screen.SeeAllMovieType
import com.example.mymovieapp.ui.see_all_screen.fragments.MovieTypeFragmentDirections
import com.example.mymovieapp.utils.ResourceProvider
import com.example.mymovieapp.utils.extensions.changeResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModels @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieResponse: Maps<MoviesResponseDomain, MoviesResponseUi>,
    private val saveMapper: Maps<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()


    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()

    private val pageToResponseFlow = MutableStateFlow(1)

    val popularMovies = pageToResponseFlow.flatMapLatest {
        repository.getPopularMovies(it)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val relevanceMovies = pageToResponseFlow.flatMapLatest {
        repository.getUpcomingMovies(it)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val publishedAtMovies = pageToResponseFlow.flatMapLatest {
        repository.getNowPlayingMovies(it)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val ratingMovies = pageToResponseFlow.flatMapLatest {
        repository.getTopRatedMovies(it)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val trendingMovies = pageToResponseFlow.flatMapLatest {
        repository.getTrendingMovies(it)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(saveMapper.map(movie))
    }

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    fun deleteMovie(movieId: Int) =
        viewModelScope.launch { storageRepository.delete(movieId = movieId) }

    fun launchMoviesTypeFragment(movieType: SeeAllMovieType) =
        navigate(MovieFragmentDirections.actionNavMoviesToMovieTypeFragment(movieType))

    fun launchMovieDetails(item: MovieUi) =
        navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(item))

    fun launchFromMovieTypeToDetails(item: MovieUi) =
        navigate(MovieTypeFragmentDirections.actionMovieTypeFragmentToMovieDetailsFragment(item))

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }
}
