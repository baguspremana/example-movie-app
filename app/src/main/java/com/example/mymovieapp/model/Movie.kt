package com.example.mymovieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class Movie (
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val release_date: String,
    val overview: String,
    val backdrop_path: String?
)