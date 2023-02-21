package com.example.mymovieapp.ui.movie.movies_screen.router

import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.navigation.toNavCommand
import com.example.mymovieapp.ui.movie.movies_screen.MovieFragmentDirections
import com.example.mymovieapp.ui.movie.search_screen.SearchFragmentDirections
import com.example.mymovieapp.ui.movie.see_all_screen.MovieSeeAllScreenFragmentDirections
import com.example.mymovieapp.ui.storage_screen.StorageFragmentDirections
import com.example.mymovieapp.ui.type.SeeAllMovieType
import javax.inject.Inject

class FragmentBaseMovieRouterImpl @Inject constructor() : FragmentBaseMovieRouter {
    override fun navigateToMoviesTypeFragment(movieType: SeeAllMovieType) = MovieFragmentDirections
        .actionNavMoviesToMovieTypeFragment(movieType)
        .toNavCommand()

    override fun navigateToMovieDetails(movie: MovieUi) =
        MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movie)
            .toNavCommand()

    override fun navigateFromSearchToMovieDetailsFragment(movie: MovieUi) =
        SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movie)
            .toNavCommand()

    override fun navigateMovieTypeToDetails(movie: MovieUi) =
        MovieSeeAllScreenFragmentDirections.actionMovieTypeFragmentToMovieDetailsFragment(movie)
            .toNavCommand()

    override fun navigateStorageToDetailsFragment(movie: MovieUi) =
        StorageFragmentDirections.actionStorageFragmentToMovieDetailsFragment(movie)
            .toNavCommand()

}