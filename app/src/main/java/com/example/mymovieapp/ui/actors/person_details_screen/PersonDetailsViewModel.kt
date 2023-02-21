package com.example.mymovieapp.ui.actors.person_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repository.MovieStorageRepository
import com.example.domain.repository.PersonRepository
import com.example.mymovieapp.app.base.BaseViewModel
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import com.example.mymovieapp.app.utils.ResourceProvider
import com.example.mymovieapp.ui.actors.persons_screen.router.FragmentPersonRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonDetailsViewModel constructor(
    private val personId: Int,
    private val storageRepository: MovieStorageRepository,
    private val repository: PersonRepository,
    private val saveMapper: Mapper<MovieUi, MovieDomain>,
    private val mapPersonDetailsDomainToUi: Mapper<PersonDetailsDomain, PersonDetailsPresentation>,
    private val resourceProvider: ResourceProvider,
    private val router: FragmentPersonRouter
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

    fun launchMovieDetails(movie: MovieUi) = navigate(
        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movie))
}