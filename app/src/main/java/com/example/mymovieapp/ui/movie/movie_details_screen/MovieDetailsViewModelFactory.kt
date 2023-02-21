package com.example.mymovieapp.ui.movie.movie_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.base.Mapper
import com.example.domain.models.movie.*
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.MovieStorageRepository
import com.example.mymovieapp.app.models.movie.CreditsResponseUi
import com.example.mymovieapp.app.models.movie.MovieDetailsUi
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.MoviesResponseUi
import com.example.mymovieapp.app.utils.ResourceProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


private const val MOVIE_ID_KEY = "movie_id_key"

class MovieDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(MOVIE_ID_KEY) private val movieId: Int,
    private val movieRepository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: Mapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponseDomainToUi: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapCreditsResponseDomainToUi: Mapper<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieUiToDomain: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsViewModel::class.java)
        return MovieDetailsViewModel(
            movieId = movieId,
            movieRepository = movieRepository,
            storageRepository = storageRepository,
            mapMovieDetailsDomainToUi = mapMovieDetailsDomainToUi,
            mapMovieResponseDomainToUi = mapMovieResponseDomainToUi,
            mapCreditsResponseDomainToUi = mapCreditsResponseDomainToUi,
            mapMovieUiToDomain = mapMovieUiToDomain,
            resourceProvider = resourceProvider,
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(MOVIE_ID_KEY) movieId: Int,
        ): MovieDetailsViewModelFactory
    }
}