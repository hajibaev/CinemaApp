package com.example.mymovieapp.ui.movie.movies_screen

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import androidx.fragment.app.viewModels
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieBinding
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModels>(FragmentMovieBinding::inflate),
    RvClickListener<MovieUi> {
    override val viewModel: MovieViewModels by viewModels()

    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val topAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val upcomingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val trendingMoviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val dramaAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val familyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val comedyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val documentaryAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val historyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val mysteryAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val westernAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popularText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.POPULAR) }
            nowplayingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.UPCOMING) }
            topText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.TOP_RATED) }
            upcomingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.NOW_PLAYING) }
            trendingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.TRENDING) }
            genresText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.DRAMATYPE) }
            familyText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.FAMILYTYPE) }
            comedyText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.COMEDY) }
            documentaryText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.DOCUMENTARY) }
            historyText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.HISTORY) }
            mysteryText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.MYSTERY) }
            westernText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.WESTERN) }
        }
    }

    private fun setAdapter() = with(requireBinding()) {
        popularRv.adapter = moviesAdapter
        nowplayingRv.adapter = nowPlayingAdapter
        tvTopretedRv.adapter = upcomingAdapter
        topRv.adapter = topAdapter
        trendingRv.adapter = trendingMoviesAdapter
        genressRv.adapter = dramaAdapter
        familyRv.adapter = familyAdapter
        comedyRv.adapter = comedyAdapter
        documentaryRv.adapter = documentaryAdapter
        historyRv.adapter = historyAdapter
        mysteryRv.adapter = mysteryAdapter
        westernRv.adapter = westernAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            popularMovies.observe {
                moviesAdapter.moviesList = it.movies
                uiVisibility()
            }
            ratingMovies.observe { topAdapter.moviesList = it.movies }
            publishedAtMovies.observe { upcomingAdapter.moviesList = it.movies }
            relevanceMovies.observe { nowPlayingAdapter.moviesList = it.movies }
            trendingMovies.observe { trendingMoviesAdapter.moviesList = it.movies }
            dramaMovies.observe { dramaAdapter.moviesList = it.movies }
            familyMovies.observe { familyAdapter.moviesList = it.movies }
            comedyMovies.observe { comedyAdapter.moviesList = it.movies }
            documentaryMovies.observe { documentaryAdapter.moviesList = it.movies }
            historyMovies.observe { historyAdapter.moviesList = it.movies }
            mysteryMovies.observe { mysteryAdapter.moviesList = it.movies }
            westernMovies.observe { westernAdapter.moviesList = it.movies }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().apply {
                shimmerLayout.stopShimmer()
                shimmerLayout.hideView()
                constLayout.hideView()
                progress.showView()
            }
        }
    }

    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = INVISIBLE
        shimmerLayout.stopShimmer()
        shimmerLayout.hideView()
        constLayout.showView()
    }

    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
        Animatoo.animateDiagonal(requireActivity())
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}
