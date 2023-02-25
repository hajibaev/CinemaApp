package com.example.mymovieapp.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.movie.room.MovieDatabase
import com.example.data.storage.tv.room.TvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "movie.db"
private const val TV_NAME = "tv.db"

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()


    @Provides
    @Singleton
    fun provideTvDatabase(@ApplicationContext context: Context): TvDatabase = Room.databaseBuilder(
        context,
        TvDatabase::class.java,
        TV_NAME
    ).build()

    @Provides
    @Singleton
    fun provideTVDao(database: TvDatabase) = database.tvDao()


}
