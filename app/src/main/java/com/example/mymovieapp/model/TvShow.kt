package com.example.mymovieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
class TvShow (
    @PrimaryKey
    val id: Int,
    val original_name: String,
    val vote_average: Double,
    val poster_path: String,
    val first_air_date: String,
    val overview: String,
    val backdrop_path: String?
)