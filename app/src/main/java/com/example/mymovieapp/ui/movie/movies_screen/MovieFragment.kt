package com.example.mymovieapp.ui.movie.movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SimpleCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentMovieBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModels>(FragmentMovieBinding::inflate),
    RvClickListener<MovieUi> {
    override val viewModel: MovieViewModels by viewModels()

    private val popularAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val topRatedAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val upcomingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val trendingMoviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
        setupClickers()
        setUi()
    }

    private fun setUi() = with(requireBinding()) {
        popular.title.text = getString(R.string.popular_category)
        upcoming.title.text = getString(R.string.adventure_text)
        topRated.title.text = getString(R.string.top_rated_category)
        nowPlaying.title.text = getString(R.string.now_playing_category)
        trending.title.text = getString(R.string.trending_category)
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popular.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.POPULAR) }
            upcoming.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.UPCOMING) }
            topRated.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.TOP_RATED) }
            nowPlaying.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.NOW_PLAYING) }
            trending.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.TRENDING) }
        }
    }

    private fun setAdapter() = with(requireBinding()) {
        popular.rv.adapter = popularAdapter
        nowPlaying.rv.adapter = nowPlayingAdapter
        topRated.rv.adapter = topRatedAdapter
        upcoming.rv.adapter = upcomingAdapter
        trending.rv.adapter = trendingMoviesAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            popularMovies.observe { popularAdapter.moviesList = it.movies }
            topRatedMovies.observe { topRatedAdapter.moviesList = it.movies }
            nowPlayingMovies.observe { nowPlayingAdapter.moviesList = it.movies }
            upcomingMovies.observe { upcomingAdapter.moviesList = it.movies }
            trendingMovies.observe {
                trendingMoviesAdapter.moviesList = it.movies
                uiVisibility()
            }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().apply {
                shimmerLayout.stopShimmer()
                shimmerLayout.hideView()
                itemMovie.showView()
            }
        }
    }


    private fun uiVisibility() = with(requireBinding()) {
        shimmerLayout.stopShimmer()
        shimmerLayout.hideView()
        itemMovie.showView()
    }

    override fun onItemClick(movie: MovieUi) = viewModel.launchMovieDetails(movie.movieId)


    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view)
            .showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}
