package com.example.mymovieapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mymovieapp.adapter.MovieAdapter
import com.example.mymovieapp.interfaces.MovieClickListener
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment(), MovieClickListener {

    private lateinit var movieViewModel: MovieViewModel
    companion object {
        const val TYPE_MOVIE = "movie"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_movie.setHasFixedSize(true)
        movieViewModel.setMovie()
        movieViewModel.getMovies().observe(viewLifecycleOwner, showMovies)
    }

    private val showMovies = Observer<List<Movie>> {movies ->
        val movieAdapter = view?.context?.let { MovieAdapter(it, movies, this) }
        view?.rv_movie?.adapter = movieAdapter
    }

    override fun onClick(position: Int, movies: List<Movie>) {
        val intent = Intent(view?.context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, movies[position].id)
        intent.putExtra(DetailActivity.TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}
