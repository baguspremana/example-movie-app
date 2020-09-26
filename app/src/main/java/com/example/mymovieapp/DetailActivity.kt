package com.example.mymovieapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.TvShow
import com.example.mymovieapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie_id"
        const val TYPE = "type"
    }

    private lateinit var detailViewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        val movieId = intent.getIntExtra(MOVIE_ID, 0)
        val type = intent.getStringExtra(TYPE)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (type == MovieFragment.TYPE_MOVIE) {
            detailViewModel.setDetailMovie(movieId)
            detailViewModel.getDetailMovie()
                .observe(this, showDetailMovie)
        } else if (type == TvShowFragment.TYPE_TV) {
            detailViewModel.setDetailTvShow(movieId)
            detailViewModel.getDetailTvShow()
                .observe(this, showDetailTvShow)
        }

    }

    private fun addFavorite(type: String?, movie: Movie?, tvShow: TvShow?) {
        fab_favorite.setOnClickListener {
            if (type == MovieFragment.TYPE_MOVIE) {
                val check = detailViewModel.getDetailMovieFavorite(movie!!.id)
                if (check != null) {
                    detailViewModel.removeFavoriteMovie(movie)
                    Toast.makeText(this, "${movie.title} dihapus dari favorite", Toast.LENGTH_SHORT).show()
                    setFabColorNotFavorite()
                } else {
                    detailViewModel.favoriteMovie(movie)
                    Toast.makeText(this, "${movie.title} ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
                    setFabColorFavorite()
                }
            } else if (type == TvShowFragment.TYPE_TV) {
                val check = detailViewModel.getDetailMovieFavorite(tvShow!!.id)
                if (check != null) {
                    detailViewModel.removeFavoriteTvShow(tvShow)
                    Toast.makeText(this, "${tvShow.original_name} dihapus dari favorite", Toast.LENGTH_SHORT).show()
                    setFabColorNotFavorite()
                } else {
                    detailViewModel.favoriteTvShow(tvShow)
                    Toast.makeText(this, "${tvShow.original_name} ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
                    setFabColorFavorite()
                }
            }
        }
    }

    private val showDetailMovie = Observer<Movie> {
        val backdropUrl = "${BuildConfig.URL_IMG}w500${it.backdrop_path}"
        val posterUrl = "${BuildConfig.URL_IMG}w185${it.poster_path}"

        tv_rate_movie_detail.text = "${it.vote_average}"
        rate_movie_detail.rating = it.vote_average.toFloat()/2
        tv_title_detail.text = it.title
        tv_release_date_detail.text = "Release: ${it.release_date}"
        Glide.with(this).load(backdropUrl).into(iv_backdrop)
        Glide.with(this).load(posterUrl).into(iv_movie_detail)
        tv_overview_detail.text = it.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_overview_detail.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        setUpCollapsingToolbar(it.title)
        addFavorite(MovieFragment.TYPE_MOVIE, it, null)
        val check = detailViewModel.getDetailMovieFavorite(it.id)
        if (check != null) {
            setFabColorFavorite()
        } else {
            setFabColorNotFavorite()
        }
    }

    private val showDetailTvShow = Observer<TvShow> {
        val backdropUrl = "${BuildConfig.URL_IMG}w500${it.backdrop_path}"
        val posterUrl = "${BuildConfig.URL_IMG}w185${it.poster_path}"

        tv_rate_movie_detail.text = "${it.vote_average}"
        rate_movie_detail.rating = it.vote_average.toFloat()/2
        tv_title_detail.text = it.original_name
        tv_release_date_detail.text = "Release: ${it.first_air_date}"
        Glide.with(this).load(backdropUrl).into(iv_backdrop)
        Glide.with(this).load(posterUrl).into(iv_movie_detail)
        tv_overview_detail.text = it.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_overview_detail.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        setUpCollapsingToolbar(it.original_name)
        addFavorite(TvShowFragment.TYPE_TV, null, it)
        val check = detailViewModel.getDetailTvShowFavorite(it.id)
        if (check != null) {
            setFabColorFavorite()
        } else {
            setFabColorNotFavorite()
        }
    }

    private fun setUpCollapsingToolbar(title: String) {
        collapsing_toolbar.title = title
        collapsing_toolbar.setExpandedTitleColor(ContextCompat.getColor(applicationContext, android.R.color.white))
    }

    private fun setFabColorNotFavorite() {
        fab_favorite.setColorFilter(
            ContextCompat.getColor(applicationContext, android.R.color.white)
        )
    }

    private fun setFabColorFavorite() {
        fab_favorite.setColorFilter(
            ContextCompat.getColor(applicationContext, android.R.color.holo_orange_light)
        )
    }
}
