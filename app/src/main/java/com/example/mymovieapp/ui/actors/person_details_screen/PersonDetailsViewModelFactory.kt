package com.example.mymovieapp.ui.actors.person_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repository.MovieStorageRepository
import com.example.domain.repository.PersonRepository
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import com.example.mymovieapp.app.utils.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val ACTORS_IDS_KEY = "actors_ids_key"

class PersonDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(ACTORS_IDS_KEY) private val personId: Int,
    private val repository: PersonRepository,
    private val mapPersonDetailsDomainToUi: Mapper<PersonDetailsDomain, PersonDetailsPresentation>,
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PersonDetailsViewModel::class.java)
        return PersonDetailsViewModel(
            personId = personId,
            repository = repository,
            storageRepository = storageRepository,
            saveMapper = saveMapper,
            mapPersonDetailsDomainToUi = mapPersonDetailsDomainToUi,
            resourceProvider = resourceProvider,
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(ACTORS_IDS_KEY) actorsIds: Int,
        ): PersonDetailsViewModelFactory
    }
}