package com.example.mymovieapp.models.person

class PersonsPresentation(
    val page: Int,
    val persons: List<PersonPresentation>,
    val total_results: Int,
    val total_pages: Int
)