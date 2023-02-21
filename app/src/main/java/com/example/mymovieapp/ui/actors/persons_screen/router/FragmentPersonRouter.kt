package com.example.mymovieapp.ui.actors.persons_screen.router

import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonPresentation
import com.example.mymovieapp.app.utils.navigation.NavCommand

interface FragmentPersonRouter {
    fun navigateToPersonDetails(person: PersonPresentation): NavCommand
    fun navigateFromPersonDetailsToMovieDetails(movieUi: MovieUi): NavCommand
}