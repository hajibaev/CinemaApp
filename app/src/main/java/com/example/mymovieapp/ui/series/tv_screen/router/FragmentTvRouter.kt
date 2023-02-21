package com.example.mymovieapp.ui.series.tv_screen.router

import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.navigation.NavCommand
import com.example.mymovieapp.ui.type.SeeAllTvType

interface FragmentTvRouter {
    fun navigateFromTvToDetailsFragment(seriesUi: SeriesUi): NavCommand
    fun navigateFromSeeAllTvTypeToDetails(seriesUi: SeriesUi): NavCommand
    fun navigateFromTvToSeeAllTvTypeFragment(seeAllTvType: SeeAllTvType): NavCommand
    fun navigateStorageToDetailsFragment(seriesUi: SeriesUi): NavCommand
}