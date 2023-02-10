package com.example.mymovieapp.ui.person.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repository.MovieStorageRepository
import com.example.domain.repository.PersonRepository
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.person.PersonDetailsPresentation
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val ACTORS_IDS_KEY = "actors_ids_key"

class PersonDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(ACTORS_IDS_KEY) private val personId: Int,
    private val repository: PersonRepository,
    private val mapPersonDetailsDomainToUi: Maps<PersonDetailsDomain, PersonDetailsPresentation>,
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: Maps<MovieUi, MovieDomain>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PersonDetailsViewModel::class.java)
        return PersonDetailsViewModel(
            personId = personId,
            repository = repository,
            storageRepository = storageRepository,
            saveMapper = saveMapper,
            mapPersonDetailsDomainToUi = mapPersonDetailsDomainToUi
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(ACTORS_IDS_KEY) actorsIds: Int,
        ): PersonDetailsViewModelFactory
    }
}