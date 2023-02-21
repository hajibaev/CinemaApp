package com.example.mymovieapp.ui.series.tv_screen.router

import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.navigation.toNavCommand
import com.example.mymovieapp.ui.series.see_all_screen.SeriesSeeAllScreenFragmentDirections
import com.example.mymovieapp.ui.series.tv_screen.TvMoviesFragmentDirections
import com.example.mymovieapp.ui.storage_screen.StorageFragmentDirections
import com.example.mymovieapp.ui.type.SeeAllTvType
import javax.inject.Inject

class FragmentTvRouterImpl @Inject constructor() : FragmentTvRouter {
    override fun navigateFromTvToDetailsFragment(seriesUi: SeriesUi) =
        TvMoviesFragmentDirections.actionNavTvMoviesToTvDetailsFragment(seriesUi)
            .toNavCommand()

    override fun navigateFromSeeAllTvTypeToDetails(seriesUi: SeriesUi) =
        SeriesSeeAllScreenFragmentDirections.actionTvTypeFragmentToTvDetailsFragment(seriesUi)
            .toNavCommand()


    override fun navigateFromTvToSeeAllTvTypeFragment(seeAllTvType: SeeAllTvType) =
        TvMoviesFragmentDirections.actionNavTvMoviesToTvTypeFragment(seeAllTvType)
            .toNavCommand()

    override fun navigateStorageToDetailsFragment(seriesUi: SeriesUi) =
        StorageFragmentDirections.actionNavStorageToTvDetailsFragment(seriesUi)
            .toNavCommand()

}