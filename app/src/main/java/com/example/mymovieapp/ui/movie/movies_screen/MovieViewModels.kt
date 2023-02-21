package com.example.mymovieapp.ui.movie.movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.MoviesResponseUi
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.ui.movie.movies_screen.router.FragmentBaseMovieRouter
import com.example.mymovieapp.ui.series.tv_screen.TvMoviesFragmentViewModel
import com.example.mymovieapp.app.utils.ResourceProvider
import com.example.mymovieapp.app.utils.extensions.changeResponseState
import com.example.mymovieapp.ui.movie.see_all_screen.MovieSeeAllScreenFragmentDirections
import com.example.mymovieapp.ui.type.SeeAllMovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModels @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val saveMapper: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
    private val router: FragmentBaseMovieRouter
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

    val dramaMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.DRAMA)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val familyMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.FAMILY)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val comedyMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.ANIMATION)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val documentaryMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.COMEDY)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val historyMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.HISTORY)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val mysteryMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.MYSTERY)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val westernMovies = pageToResponseFlow.flatMapLatest {
        repository.getMoviesGenres(it, TvMoviesFragmentViewModel.WESTERN)
    }.map(mapMovieResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.currectPage, value.totalPages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(saveMapper.map(movie))
    }

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    fun launchMoviesTypeFragment(movieType: SeeAllMovieType) = navigate(
        MovieFragmentDirections.actionNavMoviesToMovieTypeFragment(movieType)
    )

    fun launchMovieDetails(movie: MovieUi) = navigate(
        MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movie)
    )

    fun launchFromMovieTypeToDetails(movie: MovieUi) = navigate(
        MovieSeeAllScreenFragmentDirections.actionMovieTypeFragmentToMovieDetailsFragment(movie)
    )

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }
}
