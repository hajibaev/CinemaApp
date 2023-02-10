package com.example.data.models.person

class PersonsData(
    val page: Int,
    val persons: List<PersonData>,
    val total_results: Int,
    val total_pages: Int
)