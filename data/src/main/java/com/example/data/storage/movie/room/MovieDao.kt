package com.example.data.storage.movie.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.movie.MovieStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieTable")
    fun getStorageMovies(): Flow<List<MovieStorage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movie: MovieStorage)

    @Query("DELETE FROM movieTable WHERE movieId =:movieId")
    fun deleteMovieFromSaveStorage(movieId: Int)


    @Query("SELECT * FROM movieTable WHERE movieId == :movieId")
     fun getMovie(movieId: Int): Flow<MovieStorage>
}