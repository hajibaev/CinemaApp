package com.example.mymovieapp.ui.movie.movies_screen.router

import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.navigation.NavCommand
import com.example.mymovieapp.ui.type.SeeAllMovieType

interface FragmentBaseMovieRouter {
    fun navigateToMoviesTypeFragment(movieType: SeeAllMovieType): NavCommand
    fun navigateToMovieDetails(movie: MovieUi): NavCommand
    fun navigateFromSearchToMovieDetailsFragment(movie: MovieUi): NavCommand
    fun navigateMovieTypeToDetails(movie: MovieUi): NavCommand
    fun navigateStorageToDetailsFragment(movie: MovieUi): NavCommand
}