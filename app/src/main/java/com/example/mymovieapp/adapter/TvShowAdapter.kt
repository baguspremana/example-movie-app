package com.example.mymovieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.R
import com.example.mymovieapp.interfaces.TvShowClickListener
import com.example.mymovieapp.model.TvShow
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowAdapter (
    private val context: Context,
    private val tvShow: List<TvShow>,
    private val listener: TvShowClickListener
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = tvShow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urlPoster = "${BuildConfig.URL_IMG}w185${tvShow[position].poster_path}"

        holder.itemView.tv_title.text = tvShow[position].original_name
        holder.itemView.tv_rate_movie.text = "${tvShow[position].vote_average}"
        holder.itemView.rate_movie.rating = tvShow[position].vote_average.toFloat()/2
        Glide.with(context).load(urlPoster).into(holder.itemView.iv_movie)
        holder.itemView.tv_release_date.text = "Relase: ${tvShow[position].first_air_date}"

        holder.itemView.item_movies.setOnClickListener {
            listener.onClick(position, tvShow)
        }
    }

}