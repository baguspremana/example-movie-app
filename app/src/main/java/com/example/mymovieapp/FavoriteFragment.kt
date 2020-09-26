package com.example.mymovieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymovieapp.favoriteui.MovieFavoriteFragment
import com.example.mymovieapp.favoriteui.TvShowFavoriteFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val page = FragmentPagerItems(view.context)
        page.add(FragmentPagerItem.of("Movie", MovieFavoriteFragment::class.java))
        page.add(FragmentPagerItem.of("TvShow", TvShowFavoriteFragment::class.java))

        val adapterFragment = FragmentPagerItemAdapter(childFragmentManager, page)
        view_pager.adapter = adapterFragment
        view_pager_tab.setViewPager(view_pager)
    }

}
