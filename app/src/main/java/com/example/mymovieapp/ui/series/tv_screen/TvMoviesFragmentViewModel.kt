package com.example.mymovieapp.ui.series.tv_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.movie.TvSeriesResponseUi
import com.example.mymovieapp.app.utils.ResourceProvider
import com.example.mymovieapp.app.utils.extensions.changeResponseState
import com.example.mymovieapp.ui.series.see_all_screen.SeriesSeeAllScreenFragmentDirections
import com.example.mymovieapp.ui.type.SeeAllTvType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvMoviesFragmentViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapTvResponse: Mapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: Mapper<SeriesUi, SeriesDomain>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()
    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)

    private val genresFLow = MutableStateFlow("")

    private val pageAndGenresFlow = pageToResponseFlow.combine(genresFLow) { page, genres ->
        Pair(page, genres)
    }

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


    val allGenres = pageAndGenresFlow.flatMapLatest {
        repository.getFantasyMovies(it.first, it.second)
    }.map(mapTvResponse::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach { value -> settings(value.page, value.total_pages) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveMovie(tv: SeriesUi) = viewModelScope.launch {
        storageRepository.tvSave(saveMapper.map(tv))
    }

    fun launchTvType(seeAllTvType: SeeAllTvType) = navigate(
        TvMoviesFragmentDirections.actionNavTvMoviesToTvTypeFragment(seeAllTvType)
    )

    fun launchTvDetails(tvId: Int) = navigate(
        TvMoviesFragmentDirections.actionNavTvMoviesToTvDetailsFragment(tvId)
    )

    fun launchFromTvTypeToDetails(tvId: Int) = navigate(
        SeriesSeeAllScreenFragmentDirections.actionTvTypeFragmentToTvDetailsFragment(tvId)
    )
    fun genresTryEmit(newGenres: String) = genresFLow.tryEmit(newGenres)

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }


    companion object {
        const val ACTION = "28"
        const val ACTIONTV = "10759"
        const val ADVENTURE = "12"
        const val ANIMATION = "16"
        const val COMEDY = "35"
        const val CRIME = "80"
        const val DOCUMENTARY = "99"
        const val DRAMA = "18"
        const val FAMILY = "10751"
        const val FANTASY = "14"
        const val HISTORY = "36"
        const val HORROR = "27"
        const val MUSIC = "10402"
        const val MYSTERY = "9648"
        const val ROMANCE = "10749"
        const val SCIENCEFICTION = "878"
        const val TV_MOVIE = "10770"
        const val THRILLER = "53"
        const val WAR = "10752"
        const val WESTERN = "37"
        const val KIDS = "10762"
        const val NEWS = "10763"
        const val REALITY = "10764"
        const val FANTASYTV = "10765"
        const val SOAP = "10766"
        const val TALK = "10767"
        const val POLITICS = "10768"

    }

}
