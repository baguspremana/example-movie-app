package com.example.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.server.Common
import com.example.mymovieapp.server.response.MResponses
import com.example.mymovieapp.sqlite.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movies = MutableLiveData<List<Movie>>()
    private var key = BuildConfig.API_KEY

    fun setMovie() {
        val apiRest = Common.apiRest
        apiRest.fetchMovie(key).enqueue(object : Callback<MResponses> {
            override fun onFailure(call: Call<MResponses>, t: Throwable) {
                movies.value = null
            }

            override fun onResponse(call: Call<MResponses>, response: Response<MResponses>) {
                if (response.body() != null) {
                    movies.value = response.body()!!.results
                }
            }

        })
    }

    fun getMovies() : LiveData<List<Movie>> = movies

    fun getAllFavoriteMovie(): LiveData<List<Movie>> {
        return  AppDatabase.getInstance().movieDao().getAllFavoriteMovie()
    }
}