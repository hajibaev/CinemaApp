package com.example.mymovieapp.ui.person.details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repository.MovieStorageRepository
import com.example.domain.repository.PersonRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.person.PersonDetailsPresentation
import com.example.mymovieapp.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonDetailsViewModel constructor(
    private val personId: Int,
    private val storageRepository: MovieStorageRepository,
    private val repository: PersonRepository,
    private val saveMapper: Maps<MovieUi, MovieDomain>,
    private val mapPersonDetailsDomainToUi: Maps<PersonDetailsDomain, PersonDetailsPresentation>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personIdFlow = MutableStateFlow(personId)

    val personFlow = personIdFlow.flatMapLatest {
        repository.getPersonDetails(it)
    }.map(mapPersonDetailsDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(saveMapper.map(movie))
    }

    fun launchMovieDetails(movie: MovieUi) =
        navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movie)
        )

}