package com.example.mymovieapp.ui.movie.search_screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SimpleCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.extensions.launchWhenViewStarted
import com.example.mymovieapp.app.utils.extensions.makeToast
import com.example.mymovieapp.app.utils.extensions.showView
import com.example.mymovieapp.databinding.FragmentSearchBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate),
    RvClickListener<MovieUi> {
    override val viewModel: SearchViewModel by viewModels()


    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        adapterAnim()
        setUi()
    }

    private fun observe(query: String) = launchWhenViewStarted {
        viewModel.searchMovie().observe { moviesAdapter.moviesList = it.movies }
        viewModel.newTryEmitQuery(query)
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
    }

    private fun setUi() = with(requireBinding()) {
        searchRv.adapter = moviesAdapter
    }

    private fun search() = requireBinding().searchView.apply {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                observe(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                observe(newText!!)
                return true
            }
        })
        setOnCloseListener {
            false
        }
    }

    private fun adapterAnim() = with(requireBinding()) {
        searchRv.itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_portrait_item,
                SlideInTopCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }


    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}