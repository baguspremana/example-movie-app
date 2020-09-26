package com.example.mymovieapp.server.response

import com.example.mymovieapp.model.TvShow
import com.google.gson.annotations.SerializedName

class TvShowResponse (
    @SerializedName("results")
    val result: List<TvShow>
)