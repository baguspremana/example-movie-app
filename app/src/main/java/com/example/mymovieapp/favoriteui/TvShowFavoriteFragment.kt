package com.example.mymovieapp.favoriteui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mymovieapp.DetailActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.TvShowFragment
import com.example.mymovieapp.adapter.TvShowAdapter
import com.example.mymovieapp.interfaces.TvShowClickListener
import com.example.mymovieapp.model.TvShow
import com.example.mymovieapp.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

class TvShowFavoriteFragment : Fragment(), TvShowClickListener {

    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_tv_show.setHasFixedSize(true)
        tvShowViewModel.getAllTvShowFavorite().observe(viewLifecycleOwner, showTvShowFavorite)
    }

    private val showTvShowFavorite = Observer<List<TvShow>> {
        if (it.isNotEmpty()) {
            val adapter = view?.context?.let { context -> TvShowAdapter(context, it, this)  }
            view?.rv_tv_show?.adapter = adapter
        } else {
            Toast.makeText(view?.context, "Data Favorite Tv Show Kosong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(position: Int, tvShow: List<TvShow>) {
        val intent = Intent(view?.context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, tvShow[position].id)
        intent.putExtra(DetailActivity.TYPE, TvShowFragment.TYPE_TV)
        startActivity(intent)
    }
}