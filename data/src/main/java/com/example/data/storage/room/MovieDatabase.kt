package com.example.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.TypeConverter
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.tv.models.TvStorage

@Database(entities = [MovieStorage::class, TvStorage::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}