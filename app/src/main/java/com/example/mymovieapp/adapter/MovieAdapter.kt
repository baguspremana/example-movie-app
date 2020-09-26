package com.example.mymovieapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.R
import com.example.mymovieapp.interfaces.MovieClickListener
import com.example.mymovieapp.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (
    private val context: Context,
    private val movies: List<Movie>,
    private val listener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val urlPoster = "${BuildConfig.URL_IMG}w185${movies[position].poster_path}"
//        Log.e("url-poster", urlPoster)

        holder.itemView.tv_title.text = movies[position].title
        holder.itemView.tv_rate_movie.text = "${movies[position].vote_average}"
        holder.itemView.rate_movie.rating = movies[position].vote_average.toFloat()/2
        Glide.with(context).load(urlPoster).into(holder.itemView.iv_movie)
        holder.itemView.tv_release_date.text = "Relase: ${movies[position].release_date}"

        holder.itemView.item_movies.setOnClickListener {
            listener.onClick(position, movies)
        }
    }

}