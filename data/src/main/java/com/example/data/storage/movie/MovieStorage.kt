package com.example.data.storage.movie

import androidx.room.*
import com.example.data.storage.TypeConverter

@Entity(tableName = "movieTable")
class MovieStorage(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,

    @TypeConverters(TypeConverter::class)
    val actorsId: List<Int>,
    val originalTitle: String?,
    val originalLanguage: String,
    val movieTitle: String?,
    val backdropPath: String?,
    val rating: Double,
    val voteCount: Int,
    val isHasVideo: Boolean,
    val voteAverage: Double,
    val minimumAge: Int,
)


//
//@Database(entities = [MovieStorage::class], version = 3, exportSchema = false)
//@TypeConverters(TypeConverter::class)
//abstract class Database : RoomDatabase() {
//    abstract fun movieDao(): MovieDao
//}