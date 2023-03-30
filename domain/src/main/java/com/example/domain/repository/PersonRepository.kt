package com.example.domain.repository

import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    fun fetchAllPeopleMovies(page: Int): Flow<PersonsDomain>

    fun fetchAllPersonDetails(personId: Int): Flow<PersonDetailsDomain>

    fun fetchAllSearchPersons(query: String): Flow<PersonsDomain>
}