package com.example.mymovieapp.ui.tv_screen.tv_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.movie.TvSeriesResponseUi
import com.example.mymovieapp.ui.see_all_screen.SeeAllTvType
import com.example.mymovieapp.ui.see_all_screen.fragments.TvTypeFragmentDirections
import com.example.mymovieapp.utils.ResourceProvider
import com.example.mymovieapp.utils.extensions.changeResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvMoviesFragmentViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapTvResponse: Maps<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: Maps<SeriesUi, SeriesDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()
    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)

    val tvTrending = pageToResponseFlow.flatMapLatest {
        repository.getTrendingTvSeries(it).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val tvTopRated = pageToResponseFlow.flatMapLatest {
        repository.getTopRatedTvSeries(it).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val tvOnTheAir = pageToResponseFlow.flatMapLatest {
        repository.getOnTheAirTvSeries(it).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val tvPopular = pageToResponseFlow.flatMapLatest {
        repository.getPopularTvSeries(it).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val tvAiringToday = pageToResponseFlow.flatMapLatest {
        repository.getAiringTodayTvSeries(it).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val anime = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, ANIMATION).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val familyMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, FAMILY).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val comedyMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, COMEDY).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val historyMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, HISTORY).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val mysteryMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, MYSTERY).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val westernMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, WESTERN).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val dramaMovies = pageToResponseFlow.flatMapLatest {
        repository.getFantasyMovies(it, DRAMA).map(mapTvResponse::map)
    }.flowOn(Dispatchers.Default)
        .onEach { value -> settings(value.page, value.total_pages) }
        .catch { t: Throwable -> _error.emit(resourceProvider.handleException(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovie(tv: SeriesUi) = viewModelScope.launch {
        storageRepository.tvSave(saveMapper.map(tv))
    }

    fun deleteTV(tvId: Int) = viewModelScope.launch {
        storageRepository.tvDelete(tvId = tvId)
    }

    fun launchTvType(tv: SeeAllTvType) =
        navigate(TvMoviesFragmentDirections.actionNavTvMoviesToTvTypeFragment(tv))

    fun launchTvDetails(tv: SeriesUi) =
        navigate(TvMoviesFragmentDirections.actionNavTvMoviesToTvDetailsFragment(tv))

    fun launchFromTvTypeToDetails(tv: SeriesUi) =
        navigate(TvTypeFragmentDirections.actionTvTypeFragmentToTvDetailsFragment(tv))

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }

    companion object {
        const val FAMILY = "80"
        const val ANIMATION = "35"
        const val COMEDY = "99"
        const val HISTORY = "10749"
        const val MYSTERY = "9648"
        const val WESTERN = "37"
        const val DRAMA = "18"
    }
}
