package com.example.mymovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.movie.room.Database
import com.example.data.storage.tv.room.TvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "movie_item.db"
private const val TV_NAME = "tv_item.db"

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database = Room.databaseBuilder(
        context,
        Database::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(database: Database) = database.movieDao()


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
