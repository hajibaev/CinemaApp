package com.example.mymovieapp.ui.person.persons_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.Maps
import com.example.domain.models.person.PersonsDomain
import com.example.domain.repository.PersonRepository
import com.example.mymovieapp.base.BaseViewModel
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.models.person.PersonsPresentation
import com.example.mymovieapp.utils.ResourceProvider
import com.example.mymovieapp.utils.extensions.changeResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val mapPersonResponseFromDomain: Maps<PersonsDomain, PersonsPresentation>,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personResponsePage = MutableStateFlow(1)

    private val _personResponseState = MutableStateFlow(ResponseState())
    val personResponseState get() = _personResponseState.asStateFlow()

    val persons = personResponsePage.flatMapLatest {
        personRepository.getPeopleMovies(it)
    }.map(mapPersonResponseFromDomain::map).flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .onEach {
            _personResponseState.emit(
                changeResponseState(
                    page = it.page,
                    totalPage = it.total_pages
                )
            )
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun nextPage() = personResponsePage.tryEmit(_personResponseState.value.nextPage)

    fun previousPage() = personResponsePage.tryEmit(_personResponseState.value.previousPage)

    fun launchPersonDetails(person: PersonPresentation) =
        navigate(PersonFragmentDirections.actionPersonFragmentToPersonDetailsFragment(person))
}
