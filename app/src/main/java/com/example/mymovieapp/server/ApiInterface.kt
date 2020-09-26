package com.example.mymovieapp.server

import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.TvShow
import com.example.mymovieapp.server.response.MResponses
import com.example.mymovieapp.server.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    fun fetchMovie(@Query("api_key") apiKey: String) : Call<MResponses>

    @GET("tv/popular")
    fun fetchTv(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun fetchDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String): Call<Movie>

    @GET("tv/{movie_id}")
    fun fetchDetailTvShow(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String): Call<TvShow>
}