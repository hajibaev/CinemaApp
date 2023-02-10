package com.example.mymovieapp.ui.movie.search_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.MoviesResponseUi
import com.example.mymovieapp.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: Maps<MovieUi, MovieDomain>,
    private val mapMoviesResponse: Maps<MoviesResponseDomain, MoviesResponseUi>,
    private val repository: MovieRepository,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    fun searchMovie(keyword: String) =
        repository.getSearchMovies(query = keyword)
            .map(mapMoviesResponse::map).flowOn(Dispatchers.Default)
            .catch { error: Throwable -> _error.emit(resourceProvider.handleException(error)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(movie = saveMapper.map(movie))
    }

    fun launchMovieDetails(movie: MovieUi) =
        navigate(SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movie))
}
