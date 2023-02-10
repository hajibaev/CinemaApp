package com.example.mymovieapp.ui.see_all_screen.fragments

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieTypeBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.makeToast
import com.example.mymovieapp.ui.movie.movies_screen.MovieViewModels
import com.example.mymovieapp.ui.see_all_screen.SeeAllMovieType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieTypeFragment : BaseFragment<FragmentMovieTypeBinding, MovieViewModels>(
    FragmentMovieTypeBinding::inflate
), MovieAdapter.RecyclerOnClickListener {
    override val viewModel by viewModels<MovieViewModels>()
    private val args by navArgs<MovieTypeFragmentArgs>()

    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this)
    }
    private val upcomingAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val topAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this)
    }
    private val nowPlayingAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this)
    }
    private val trendingMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            nextBtn.setOnClickListener {
                nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnClickListener {
                previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun observeType() = with(viewModel) {
        requireBinding().apply {
            when (args.movieType) {
                SeeAllMovieType.TOP_RATED -> {
                    moviesRv.adapter = topAdapter
                    observeTopRatedMovie()
                }
                SeeAllMovieType.NOW_PLAYING -> {
                    moviesRv.adapter = nowPlayingAdapter
                    observeNowPlayingMovies()
                }
                SeeAllMovieType.UPCOMING -> {
                    moviesRv.adapter = upcomingAdapter
                    observeUpcomingMovies()
                }
                SeeAllMovieType.POPULAR -> {
                    moviesRv.adapter = moviesAdapter
                    observePopularMovies()
                }
                SeeAllMovieType.TRENDING -> {
                    moviesRv.adapter = trendingMoviesAdapter
                    observeTrendingMovies()
                }
            }
            error.onEach {
                makeToast(it, requireContext())
            }
            lifecycleScope.launchWhenResumed {
                movieResponseState.collectLatest { state ->
                    prevPageText.text = state.previousPage.toString()
                    currentPageText.text = state.page.toString()
                    nextPageText.text = state.nextPage.toString()
                    prevBtn.apply {
                        isClickable = state.isHasPreviousPage
                        isFocusable = state.isHasPreviousPage
                    }
                    nextBtn.apply {
                        isClickable = state.isHasNextPage
                        isFocusable = state.isHasNextPage
                    }
                }
            }
        }
    }

    private fun observeTopRatedMovie() {
        lifecycleScope.launchWhenResumed {
            viewModel.ratingMovies.collectLatest {
                topAdapter.moviesList = it.movies
                requireBinding().pageConstraint.visibility = View.VISIBLE
                requireBinding().isEmptyLoading.visibility = View.GONE
                requireBinding().movieName.text = getString(R.string.top_rated_category)
            }
        }
    }

    private fun observeNowPlayingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.relevanceMovies.collectLatest {
                nowPlayingAdapter.moviesList = it.movies
                requireBinding().pageConstraint.visibility = View.VISIBLE
                requireBinding().isEmptyLoading.visibility = View.GONE
                requireBinding().movieName.text = getString(R.string.now_playing_category)
            }
        }
    }


    private fun observeUpcomingMovies() = lifecycleScope.launchWhenResumed {
        viewModel.publishedAtMovies.collectLatest {
            upcomingAdapter.moviesList = it.movies
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
            requireBinding().movieName.text = getString(R.string.adventure_text)

        }
    }

    private fun observePopularMovies() = lifecycleScope.launchWhenResumed {
        viewModel.popularMovies.collectLatest {
            moviesAdapter.moviesList = it.movies
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
            requireBinding().movieName.text = getString(R.string.popular_category)

        }
    }

    private fun observeTrendingMovies() = lifecycleScope.launchWhenResumed {
        viewModel.trendingMovies.collectLatest {
            trendingMoviesAdapter.moviesList = it.movies
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
            requireBinding().movieName.text = getString(R.string.trending_category)

        }
    }


    override fun onItemClick(movie: MovieUi) {
        viewModel.launchFromMovieTypeToDetails(item = movie)
    }

    override fun onLongItemClick(movie: MovieUi) {
        viewModel.saveMovie(movie)
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}