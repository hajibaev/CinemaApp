package com.example.data.storage.movie.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.TypeConverter


@Database(entities = [MovieStorage::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}