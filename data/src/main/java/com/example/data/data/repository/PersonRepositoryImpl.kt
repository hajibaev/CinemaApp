package com.example.data.data.repository

import com.example.data.cloud.source.person.PersonsCloudDataSource
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personsCloudDataSource: PersonsCloudDataSource,
    private val mapPersonsDataToDomain: Mapper<PersonsData, PersonsDomain>,
    private val mapPersonDetailsToDomain: Mapper<PersonDetailsData, PersonDetailsDomain>
) : PersonRepository {

    override fun getPeopleMovies(page: Int): Flow<PersonsDomain> =
        personsCloudDataSource.getAllPeople(page = page)
            .map(mapPersonsDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getPersonDetails(personId: Int): Flow<PersonDetailsDomain> =
        personsCloudDataSource.getAllPersonDetails(personId = personId)
            .map(mapPersonDetailsToDomain::map)
            .flowOn(Dispatchers.Default)

    override suspend fun getSearchPeopleMovies(query: String): Flow<PersonsDomain> =
        personsCloudDataSource.getAllSearchPeopleMovies(query = query)
            .map(mapPersonsDataToDomain::map)
            .flowOn(Dispatchers.Default)

}