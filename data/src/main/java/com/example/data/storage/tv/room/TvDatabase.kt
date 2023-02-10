package com.example.data.storage.tv.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.TypeConverter
import com.example.data.storage.tv.models.TvStorage

@Database(entities = [TvStorage::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TvDatabase : RoomDatabase() {
    abstract fun tvDao(): TvDao
}
