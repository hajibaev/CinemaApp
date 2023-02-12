package com.example.domain.repository

import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPeopleMovies(page: Int): Flow<PersonsDomain>
     fun getPersonDetails(personId: Int): Flow<PersonDetailsDomain>
    suspend fun getSearchPeopleMovies(query: String): Flow<PersonsDomain>
}