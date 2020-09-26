package com.example.mymovieapp.interfaces

import com.example.mymovieapp.model.Movie

interface MovieClickListener {
    fun onClick(position: Int, movies: List<Movie>)
}