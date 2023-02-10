package com.example.mymovieapp.ui.storage_screen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentStorageBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.movie.MovieStorageAdapter
import com.example.mymovieapp.ui.adapters.movie.TvStorageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding, MovieStorageViewModel>(
    FragmentStorageBinding::inflate
), AdapterView.OnItemSelectedListener, MovieStorageAdapter.RecyclerFavOnClickListener,
    TvStorageAdapter.RecyclerFavOnClickListener {
    override val viewModel: MovieStorageViewModel by viewModels()
    private val movieAdapter by lazy {
        MovieStorageAdapter(context = requireContext(), this)
    }

    private val tvAdapter by lazy {
        TvStorageAdapter(context = requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSpinner()
        adapterAnimation()
    }

    private fun observeSpinner() = with(requireBinding()) {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.favorite,
            R.layout.spiner_item
        )
        favSpinner.adapter = arrayAdapter
        favSpinner.onItemSelectedListener = this@StorageFragment
    }

    private fun initObserver() = with(viewModel) {
        requireBinding().apply {
            savedRv.postDelayed({
                lifecycleScope.launchWhenStarted {
                    storageMovies.collectLatest {
                        movieAdapter.moviesList = it
                            isEmptyLoading.isVisible = it.isEmpty()
                    }
                }
            }, 400L)
        }
    }


    private fun tvInitObserver() = with(viewModel) {
        requireBinding().apply {
            savedRv.postDelayed({
                lifecycleScope.launchWhenStarted {
                    tvStorage.collectLatest {
                        tvAdapter.submitList(it)
                        isEmptyLoading.isVisible = it.isEmpty()
                    }
                }
            }, 400L)
        }
    }

    private fun adapterAnimation() = with(requireBinding()) {
        savedRv.itemAnimator =
            AddableItemAnimator(SlideInLeftCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(
                    R.layout.storage_item,
                    SlideInTopCommonAnimator()
                )
                animator.addDuration = 400L
                animator.removeDuration = 300L
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> {
                initObserver()
                requireBinding().savedRv.adapter = movieAdapter

            }
            1 -> {
                tvInitObserver()
                requireBinding().savedRv.adapter = tvAdapter
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = TODO()
    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onClearItemClick(movie: MovieUi) {
        viewModel.deleteMovie(movie.movieId)
    }

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.launchTvDetails(seriesUi)
    }

    override fun onTvClearItemClick(seriesUi: SeriesUi) {
        viewModel.deleteTV(seriesUi.id)
    }
}