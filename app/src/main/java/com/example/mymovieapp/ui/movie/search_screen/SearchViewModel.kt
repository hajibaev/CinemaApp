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
import com.example.mymovieapp.app.utils.ResourceProvider
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

    fun searchMovie() = query.flatMapLatest {
        repository.getSearchMovies(query = it)
    }.map(mapMoviesResponse::map).flowOn(Dispatchers.Default)
        .catch { error: Throwable -> _error.emit(resourceProvider.handleException(error)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun newTryEmitQuery(newQuery: String) = query.tryEmit(newQuery)

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(movie = saveMapper.map(movie))
    }

    fun launchMovieDetails(movie: MovieUi) = navigate(
        SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movie)
    )
}
