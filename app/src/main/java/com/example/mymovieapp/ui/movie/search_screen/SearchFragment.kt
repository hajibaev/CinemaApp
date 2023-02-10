package com.example.mymovieapp.ui.movie.search_screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentSearchBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    FragmentSearchBinding::inflate
),
    MovieAdapter.RecyclerOnClickListener {

    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this)
    }
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        setUi()
    }

    private fun observe(keyword: String) = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            searchMovie(keyword).collectLatest {
                moviesAdapter.moviesList = it.movies
                requireBinding().searchPb.visibility = View.INVISIBLE
            }
        }
        error.onEach {
            makeToast(it, requireContext())
        }
    }


    private fun setUi() = with(requireBinding()) {
        searchRv.adapter = moviesAdapter
    }

    private fun search() = requireBinding().searchView.apply {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                observe(query.toString())
//                viewModel.updateKeyword(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                viewModel.updateKeyword(newText.toString())
                observe(query.toString())

                return true
            }
        })
        setOnCloseListener {
            false
        }
    }

    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onLongItemClick(movie: MovieUi) {
        viewModel.saveMovie(movie)
        makeToast(
            "Фильм (${movie.movieTitle}) Сохранён",
            context = requireContext()
        )
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}