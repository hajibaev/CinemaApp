package com.example.mymovieapp.app.di

import com.example.mymovieapp.ui.movie.movies_screen.router.FragmentBaseMovieRouter
import com.example.mymovieapp.ui.movie.movies_screen.router.FragmentBaseMovieRouterImpl
import com.example.mymovieapp.ui.actors.persons_screen.router.FragmentPersonRouter
import com.example.mymovieapp.ui.actors.persons_screen.router.FragmentPersonRouterImpl
import com.example.mymovieapp.ui.series.tv_screen.router.FragmentTvRouter
import com.example.mymovieapp.ui.series.tv_screen.router.FragmentTvRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    abstract fun bindFragmentBaseMovieRouter(
        impl: FragmentBaseMovieRouterImpl
    ): FragmentBaseMovieRouter


    @Binds
    abstract fun bindFragmentPersonRouter(
        impl: FragmentPersonRouterImpl
    ): FragmentPersonRouter


    @Binds
    abstract fun bindFragmentTvRouter(
        impl: FragmentTvRouterImpl
    ): FragmentTvRouter

}