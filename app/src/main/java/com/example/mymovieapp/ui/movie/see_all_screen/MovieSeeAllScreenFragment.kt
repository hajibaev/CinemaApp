package com.example.mymovieapp.ui.movie.see_all_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SimpleCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentMovieTypeBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.movie.movies_screen.MovieViewModels
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieSeeAllScreenFragment : BaseFragment<FragmentMovieTypeBinding, MovieViewModels>(
    FragmentMovieTypeBinding::inflate
), RvClickListener<MovieUi> {
    override val viewModel by viewModels<MovieViewModels>()

    private val args by navArgs<MovieSeeAllScreenFragmentArgs>()

    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val upcoming by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val topAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val trendAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val dramaAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val familyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val comedyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val documentaryAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val historyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val mysteryAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val westernAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        upButton.setOnDownEffectClickListener { viewModel.navigateBack() }

        viewModel.apply {
            nextBtn.setOnDownEffectClickListener {
                nextPage()
                adapterAnimation()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnDownEffectClickListener {
                previousPage()
                adapterAnim()
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
                SeeAllMovieType.DRAMATYPE -> observeDramaMovies()
                SeeAllMovieType.FAMILYTYPE -> observeFamilyMovies()
                SeeAllMovieType.COMEDY -> observeComedyMovies()
                SeeAllMovieType.DOCUMENTARY -> observeDocumentaryMovies()
                SeeAllMovieType.HISTORY -> observeHistoryMovies()
                SeeAllMovieType.MYSTERY -> observeMysteryMovies()
                SeeAllMovieType.WESTERN -> observeWesternMovies()

            }
            error.onEach { makeToast(it, requireContext()) }
            launchWhenViewResumed { movieResponseState.observe(::observeResponseState) }
        }
    }

    private fun observePopularMovies() = with(requireBinding()) {
        launchWhenViewResumed {
            viewModel.popularMovies.observe {
                moviesAdapter.moviesList = it.movies
                uiVisibility()
            }
            moviesRv.adapter = moviesAdapter
            title.text = POPULAR_TEXT
            img.setImageResource(R.drawable.popular)
        }
    }

    private fun observeRatingMovies() = launchWhenViewResumed {
        viewModel.ratingMovies.observe {
            topAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = topAdapter
        requireBinding().title.text = TOP_RATED
        requireBinding().img.setImageResource(R.drawable.toprated)
    }

    private fun observePublishedAtMovies() = with(requireBinding()) {
        launchWhenViewResumed {
            viewModel.publishedAtMovies.observe {
                upcoming.moviesList = it.movies
                uiVisibility()
            }
            moviesRv.adapter = upcoming
            title.text = UP_COMING
            img.setImageResource(R.drawable.upcomin)
        }
    }

    private fun observeRelevanceMovies() = with(requireBinding()) {
        launchWhenViewResumed {
            viewModel.relevanceMovies.observe {
                nowPlayingAdapter.moviesList = it.movies
                uiVisibility()
            }
            moviesRv.adapter = nowPlayingAdapter
            title.text = NOW_PLAYING
            img.setImageResource(R.drawable.nowplayin)
        }
    }

    private fun observeTrendingMovies() = with(requireBinding()) {
        launchWhenViewResumed {
            viewModel.trendingMovies.observe {
                trendAdapter.moviesList = it.movies
                uiVisibility()
            }
        }
        requireBinding().moviesRv.adapter = trendAdapter
        title.text = TRENDING_TEXT
        img.setImageResource(R.drawable.trending)
    }

    private fun observeDramaMovies() = with(requireBinding()) {
        launchWhenViewResumed {
            viewModel.dramaMovies.observe {
                dramaAdapter.moviesList = it.movies
                uiVisibility()
            }
            requireBinding().moviesRv.adapter = trendAdapter
            title.text = DRAMA_TEXT
            img.setImageResource(R.drawable.drama)
        }
    }

    private fun observeFamilyMovies() = launchWhenViewResumed {
        viewModel.familyMovies.observe {
            familyAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = familyAdapter
    }

    private fun observeComedyMovies() = launchWhenViewResumed {
        viewModel.comedyMovies.observe {
            comedyAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = comedyAdapter
    }

    private fun observeDocumentaryMovies() = launchWhenViewResumed {
        viewModel.documentaryMovies.observe {
            documentaryAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = documentaryAdapter
    }

    private fun observeHistoryMovies() = launchWhenViewResumed {
        viewModel.historyMovies.observe {
            historyAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = historyAdapter
    }

    private fun observeMysteryMovies() = launchWhenViewResumed {
        viewModel.mysteryMovies.observe {
            mysteryAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = mysteryAdapter
    }

    private fun observeWesternMovies() = launchWhenViewResumed {
        viewModel.westernMovies.observe {
            westernAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = westernAdapter
    }

    private fun uiVisibility() = with(requireBinding()) {
        lifecycleScope.launch {
            delay(400L)
            pageConstraint.showView()
        }
        isEmptyLoading.hideView()
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


    private fun adapterAnimation() = with(requireBinding()) {
        moviesRv.itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_portrait_item,
                SimpleCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    private fun adapterAnim() = with(requireBinding()) {
        moviesRv.itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_portrait_item,
                SlideInTopCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.launchFromMovieTypeToDetails(movie = item)
    }


    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }

    companion object {
        const val POPULAR_TEXT = "Popular"
        const val TOP_RATED = "Top Rated"
        const val NOW_PLAYING = "Now Playing"
        const val UP_COMING = "Upcoming"
        const val TRENDING_TEXT = "Trending"
        const val DRAMA_TEXT = "Drama"
    }

}