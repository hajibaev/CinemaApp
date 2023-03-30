package com.example.data.data.repository

import com.example.data.cloud.source.person.PersonsDataSource
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personsCloudDataSource: PersonsDataSource,
    private val mapPersonsDataToDomain: Mapper<PersonsData, PersonsDomain>,
    private val mapPersonDetailsToDomain: Mapper<PersonDetailsData, PersonDetailsDomain>,
    private val dispatchers: DispatchersProvider,
) : PersonRepository {

    override fun fetchAllPeopleMovies(page: Int): Flow<PersonsDomain> =
        personsCloudDataSource.fetchAllPeople(page = page).map(mapPersonsDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllPersonDetails(personId: Int): Flow<PersonDetailsDomain> =
        personsCloudDataSource.fetchAllPersonDetails(personId = personId)
            .map(mapPersonDetailsToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllSearchPersons(query: String): Flow<PersonsDomain> =
        personsCloudDataSource.fetchAllSearchPeopleMovies(query = query)
            .map(mapPersonsDataToDomain::map)
            .flowOn(dispatchers.default())

}