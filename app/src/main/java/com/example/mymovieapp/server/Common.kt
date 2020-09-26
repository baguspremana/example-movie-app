package com.example.mymovieapp.server

object Common {
    private var url = "https://api.themoviedb.org/3/"
    val apiRest: ApiInterface get() = ApiClient.getClient(url).create(ApiInterface::class.java)
}