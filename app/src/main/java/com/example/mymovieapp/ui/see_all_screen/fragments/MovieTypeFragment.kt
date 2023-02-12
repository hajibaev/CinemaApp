package com.example.mymovieapp.ui.see_all_screen.fragments

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieTypeBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.hideView
import com.example.mymovieapp.ui.launchWhenViewResumed
import com.example.mymovieapp.ui.makeToast
import com.example.mymovieapp.ui.movie.movies_screen.MovieViewModels
import com.example.mymovieapp.ui.see_all_screen.SeeAllMovieType
import com.example.mymovieapp.ui.setOnDownEffectClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieTypeFragment : BaseFragment<FragmentMovieTypeBinding, MovieViewModels>(
    FragmentMovieTypeBinding::inflate
), MovieAdapter.RecyclerOnClickListener {
    override val viewModel by viewModels<MovieViewModels>()

    private val args by navArgs<MovieTypeFragmentArgs>()

    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val upcoming by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val topAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val trendAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            nextBtn.setOnDownEffectClickListener {
                nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnDownEffectClickListener {
                previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun observeType() = with(viewModel) {
        requireBinding().apply {
            when (args.movieType) {
                SeeAllMovieType.TOP_RATED -> observeRatingMovies()
                SeeAllMovieType.NOW_PLAYING -> observeRelevanceMovies()
                SeeAllMovieType.UPCOMING -> observePublishedAtMovies()
                SeeAllMovieType.POPULAR -> observePopularMovies()
                SeeAllMovieType.TRENDING -> observeTrendingMovies()
            }
            error.onEach { makeToast(it, requireContext()) }
            launchWhenViewResumed { movieResponseState.observe(::observeResponseState) }
        }
    }

    private fun observePopularMovies() = with(viewModel) {
        launchWhenViewResumed { popularMovies.observe { moviesAdapter.moviesList = it.movies } }
        requireBinding().moviesRv.adapter = moviesAdapter
        uiVisibility()
    }

    private fun observeRatingMovies() = with(viewModel) {
        launchWhenViewResumed { ratingMovies.observe { topAdapter.moviesList = it.movies } }
        requireBinding().moviesRv.adapter = topAdapter
        uiVisibility()
    }

    private fun observePublishedAtMovies() = with(viewModel) {
        launchWhenViewResumed { publishedAtMovies.observe { upcoming.moviesList = it.movies } }
        requireBinding().moviesRv.adapter = upcoming
        uiVisibility()
    }

    private fun observeRelevanceMovies() = with(viewModel) {
        launchWhenViewResumed { relevanceMovies.observe { nowPlayingAdapter.moviesList = it.movies }
            requireBinding().moviesRv.adapter = nowPlayingAdapter
            uiVisibility()
        }
    }

    private fun observeTrendingMovies() = with(viewModel) {
        launchWhenViewResumed { trendingMovies.observe { trendAdapter.moviesList = it.movies } }
        requireBinding().moviesRv.adapter = trendAdapter
        uiVisibility()
    }

    private fun uiVisibility() = with(requireBinding()) {
        pageConstraint.visibility = View.VISIBLE
        isEmptyLoading.visibility = View.GONE
    }

    private fun observeResponseState(state: ResponseState) = with(requireBinding()) {
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

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onItemClick(movie: MovieUi) = viewModel.launchFromMovieTypeToDetails(item = movie)

    override fun onLongItemClick(movie: MovieUi) { viewModel.saveMovie(movie)
        makeToast("Фильм (${movie.movieTitle}) Сохранён",  requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}