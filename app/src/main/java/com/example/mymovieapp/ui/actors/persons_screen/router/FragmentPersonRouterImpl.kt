package com.example.mymovieapp.ui.actors.persons_screen.router

import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonPresentation
import com.example.mymovieapp.ui.actors.person_details_screen.PersonDetailsFragmentDirections
import com.example.mymovieapp.ui.actors.persons_screen.PersonFragmentDirections
import com.example.mymovieapp.app.utils.navigation.NavCommand
import com.example.mymovieapp.app.utils.navigation.toNavCommand
import javax.inject.Inject

class FragmentPersonRouterImpl @Inject constructor() : FragmentPersonRouter {
    override fun navigateToPersonDetails(person: PersonPresentation): NavCommand =
        PersonFragmentDirections.actionPersonFragmentToPersonDetailsFragment(person)
            .toNavCommand()

    override fun navigateFromPersonDetailsToMovieDetails(movieUi: MovieUi): NavCommand =
        PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movieUi)
            .toNavCommand()
}