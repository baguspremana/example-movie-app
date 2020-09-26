package com.example.mymovieapp.sqlite.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovieapp.model.TvShow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteTvShow(tvShow: TvShow)

    @Delete
    fun removeFavoriteTvShow(tvShow: TvShow)

    @Query("Select * From tvShow where id=:id")
    fun getFavoriteTvShow(id: Int) : TvShow

    @Query("Select * From tvShow")
    fun getAllFavoriteTvShow() : LiveData<List<TvShow>>
}