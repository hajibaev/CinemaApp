package com.example.domain.repository

import com.example.domain.DataRequestState
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPeopleMovies(page: Int): Flow<PersonsDomain>
    suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsDomain>
    suspend fun getSearchPeopleMovies(query: String): Flow<PersonsDomain>
}