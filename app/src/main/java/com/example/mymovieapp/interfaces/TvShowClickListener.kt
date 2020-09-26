package com.example.mymovieapp.interfaces

import com.example.mymovieapp.model.TvShow

interface TvShowClickListener {
    fun onClick(position: Int, tvShow: List<TvShow>)
}