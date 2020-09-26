package com.example.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.model.TvShow
import com.example.mymovieapp.server.ApiInterface
import com.example.mymovieapp.server.Common
import com.example.mymovieapp.server.response.TvShowResponse
import com.example.mymovieapp.sqlite.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    private lateinit var apiRest: ApiInterface
    private val tvShow = MutableLiveData<List<TvShow>>()
    private val key = BuildConfig.API_KEY

    fun setTvShow() {
        apiRest = Common.apiRest
        apiRest.fetchTv(key).enqueue(object : Callback<TvShowResponse> {
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                tvShow.value = null
            }

            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.body() != null) {
                    tvShow.value = response.body()!!.result
                }
            }

        })
    }

    fun getTvShow() : LiveData<List<TvShow>> = tvShow

    fun getAllTvShowFavorite(): LiveData<List<TvShow>> {
        return  AppDatabase.getInstance().tvShowDao().getAllFavoriteTvShow()
    }
}