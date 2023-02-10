package com.example.data.cloud.api

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.cloud.server.Endpoints.Person.PEOPLE
import com.example.data.cloud.server.Endpoints.Person.PERSON_DETAILS
import com.example.data.cloud.server.Endpoints.Person.SEARCH_PEOPLE
import com.example.data.cloud.server.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET(PEOPLE)
    suspend fun getPeople(
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int
    ): Response<PersonsCloud>


    @GET(SEARCH_PEOPLE)
    suspend fun getSearchPeople(
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("query") query: String,
    ): Response<PersonsCloud>


    @GET(PERSON_DETAILS)
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "en",
    ): Response<PersonDetailsCloud>
}