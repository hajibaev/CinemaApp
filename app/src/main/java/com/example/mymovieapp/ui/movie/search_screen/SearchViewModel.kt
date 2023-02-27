package com.example.mymovieapp.ui.movie.search_screen

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
import com.example.mymovieapp.app.utils.ResourceProvider
import com.example.mymovieapp.app.utils.extensions.changeResponseState
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.example.mymovieapp.ui.type.SeeAllTvType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: Mapper<MovieUi, MovieDomain>,
    private val mapMoviesResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val repository: MovieRepository,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val query = MutableStateFlow("")


    private val pageToResponseFlow = MutableStateFlow(1)
    private val genresFLow = MutableStateFlow("")

    private val pageAndGenresFlow = genresFLow.combine(pageToResponseFlow) { genres, page ->
        Pair(genres, page)
    }

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()


    fun searchMovie() = query.flatMapLatest {
        repository.getSearchMovies(query = it)
    }.map(mapMoviesResponse::map).flowOn(Dispatchers.Default)
        .catch { error: Throwable -> _error.emit(resourceProvider.handleException(error)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun newTryEmitQuery(newQuery: String) = query.tryEmit(newQuery)

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(movie = saveMapper.map(movie))
    }

    fun genresTryEmit(newGenres: String) = genresFLow.tryEmit(newGenres)


    fun launchMovieType(type: SeeAllMovieType) =
        navigate(SearchFragmentDirections.actionNavSearchToMovieTypeFragment(type))

    fun launchTvType(type: SeeAllTvType) =
        navigate(SearchFragmentDirections.actionNavSearchToTvTypeFragment(type))

    fun launchMovieDetails(movieId: Int) = navigate(
        SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movieId)
    )

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }
}
