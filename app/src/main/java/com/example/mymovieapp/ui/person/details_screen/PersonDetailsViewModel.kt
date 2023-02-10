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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PersonDetailsViewModel constructor(
    private val personId: Int,
    private val storageRepository: MovieStorageRepository,
    private val repository: PersonRepository,
    private val saveMapper: Maps<MovieUi, MovieDomain>,
    private val mapPersonDetailsDomainToUi: Maps<PersonDetailsDomain, PersonDetailsPresentation>,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personIdFlow = MutableStateFlow(personId)

    val personFlow = personIdFlow.map(repository::getPersonDetails)
        .map { it.map(mapPersonDetailsDomainToUi) }

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.save(saveMapper.map(movie))
    }

    fun launchMovieDetails(movie: MovieUi) =
        navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movie)
        )

}