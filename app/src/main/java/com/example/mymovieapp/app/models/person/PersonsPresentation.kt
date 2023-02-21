package com.example.mymovieapp.app.models.person

class PersonsPresentation(
    val page: Int,
    val persons: List<PersonPresentation>,
    val total_results: Int,
    val total_pages: Int
)