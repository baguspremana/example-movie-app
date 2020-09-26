package com.example.mymovieapp.sqlite.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovieapp.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: Movie)

    @Delete
    fun removeFavoriteMovie(movie: Movie)

    @Query("Select * From movie where id=:id")
    fun getFavoriteMovie(id: Int) : Movie

    @Query("Select * From movie")
    fun getAllFavoriteMovie() : LiveData<List<Movie>>
}