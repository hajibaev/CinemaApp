package com.example.mymovieapp.ui.storage_screen

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentStorageBinding
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieStorageAdapter
import com.example.mymovieapp.ui.adapters.movie.TvStorageAdapter
import com.example.mymovieapp.app.utils.extensions.launchWhenViewResumed
import com.example.mymovieapp.app.utils.extensions.showView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding, MovieStorageViewModel>(
    FragmentStorageBinding::inflate
), AdapterView.OnItemSelectedListener, RvClickListener<MovieUi>,
    TvStorageAdapter.RecyclerFavOnClickListener {
    override val viewModel: MovieStorageViewModel by viewModels()

    private val movieAdapter by lazy { MovieStorageAdapter(requireContext(), this) }
    private val tvAdapter by lazy { TvStorageAdapter(requireContext(), this) }

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
        requireBinding().savedRv.postDelayed({
            launchWhenViewResumed { storageMovies.observe { movieAdapter.submitList(it) } }
        }, DEFAULT_ITEMS_ANIMATOR_DURATION)
    }

    private fun tvInitObserver() = with(viewModel) {
        requireBinding().savedRv.postDelayed({
            launchWhenViewResumed { tvStorage.observe { tvAdapter.submitList(it) } }
        }, DEFAULT_ITEMS_ANIMATOR_DURATION)
    }

    private fun adapterAnimation() = with(requireBinding()) {
        savedRv.itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.storage_item,
                SlideInTopCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
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

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.launchTvDetails(seriesUi)
    }

    override fun onTvClearItemClick(seriesUi: SeriesUi) {
        viewModel.deleteTV(seriesUi.id)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view)
            .showView()
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.launchMovieDetails(item)
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.deleteMovie(item.movieId)
        showSuccessSnackBar("Movie ${item.movieTitle} delete")
    }
}