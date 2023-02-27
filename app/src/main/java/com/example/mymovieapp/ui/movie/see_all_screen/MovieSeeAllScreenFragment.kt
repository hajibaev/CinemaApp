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
import com.example.mymovieapp.ui.movie.search_screen.SearchViewModel
import com.example.mymovieapp.ui.see_more_sort_dialog.SettingsFragment
import com.example.mymovieapp.ui.series.tv_screen.TvMoviesFragmentViewModel
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.example.ui_core.modal_page.ModalPage
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

    private val popularAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val topRatedAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val upcomingAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val trendingMoviesAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val dramaAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val crimeAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val animationAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val comedyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val historyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val mysteryAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val westernAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val actionAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val adventureAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val documentaryAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val familyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val fantasyAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val horrorAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val musicAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val romanceAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val sciencefictionAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val tvAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val thrillerAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }
    private val warAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType()
        setupClickers()
        observe()
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClick { viewModel.navigateBack() }
        viewModel.apply {
            nextBtn.setOnDownEffectClick {
                nextPage()
                adapterAnimation()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnDownEffectClick {
                previousPage()
                adapterAnim()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }


    private fun observe() = with(viewModel) {
        launchWhenViewStarted {
            allGenres.observe {
                dramaAdapter.moviesList = it.movies
                familyAdapter.moviesList = it.movies
                documentaryAdapter.moviesList = it.movies
                comedyAdapter.moviesList = it.movies
                historyAdapter.moviesList = it.movies
                mysteryAdapter.moviesList = it.movies
                westernAdapter.moviesList = it.movies
                actionAdapter.moviesList = it.movies
                adventureAdapter.moviesList = it.movies
                animationAdapter.moviesList = it.movies
                crimeAdapter.moviesList = it.movies
                fantasyAdapter.moviesList = it.movies
                horrorAdapter.moviesList = it.movies
                musicAdapter.moviesList = it.movies
                romanceAdapter.moviesList = it.movies
                sciencefictionAdapter.moviesList = it.movies
                tvAdapter.moviesList = it.movies
                thrillerAdapter.moviesList = it.movies
                warAdapter.moviesList = it.movies
                uiVisibility()
            }
        }
    }

    private fun observeType() = with(viewModel) {
        requireBinding().apply {
            when (args.movieType) {
                SeeAllMovieType.TOP_RATED -> observeTopRated()
                SeeAllMovieType.NOW_PLAYING -> observeNowPlaying()
                SeeAllMovieType.UPCOMING -> observeUpcoming()
                SeeAllMovieType.POPULAR -> observePopular()
                SeeAllMovieType.TRENDING -> observeTrending()
                SeeAllMovieType.DRAMA -> {
                    genresTryEmit(TvMoviesFragmentViewModel.DRAMA)
                    requireBinding().moviesRv.adapter = dramaAdapter
                    toolbarTitle(getString(R.string.drama))
                }
                SeeAllMovieType.FAMILY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.FAMILY)
                    toolbarTitle(getString(R.string.family_text))
                    requireBinding().moviesRv.adapter = familyAdapter
                }
                SeeAllMovieType.COMEDY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.COMEDY)
                    toolbarTitle(getString(R.string.comedy))
                    requireBinding().moviesRv.adapter = comedyAdapter
                }
                SeeAllMovieType.DOCUMENTARY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.DOCUMENTARY)
                    toolbarTitle(getString(R.string.documentary))
                    requireBinding().moviesRv.adapter = documentaryAdapter
                }
                SeeAllMovieType.HISTORY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.HISTORY)
                    toolbarTitle(getString(R.string.history))
                    requireBinding().moviesRv.adapter = historyAdapter
                }
                SeeAllMovieType.MYSTERY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.MYSTERY)
                    toolbarTitle(getString(R.string.mystery))
                    requireBinding().moviesRv.adapter = mysteryAdapter
                }
                SeeAllMovieType.WESTERN -> {
                    genresTryEmit(TvMoviesFragmentViewModel.WESTERN)
                    toolbarTitle(getString(R.string.western))
                    requireBinding().moviesRv.adapter = westernAdapter
                }
                SeeAllMovieType.ACTION -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ACTION)
                    toolbarTitle(getString(R.string.action))
                    requireBinding().moviesRv.adapter = actionAdapter
                }
                SeeAllMovieType.ADVENTURE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ADVENTURE)
                    toolbarTitle(getString(R.string.adventure))
                    requireBinding().moviesRv.adapter = adventureAdapter
                }
                SeeAllMovieType.ANIMATION -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ANIMATION)
                    toolbarTitle(getString(R.string.animation))
                    requireBinding().moviesRv.adapter = animationAdapter
                }
                SeeAllMovieType.CRIME -> {
                    genresTryEmit(TvMoviesFragmentViewModel.CRIME)
                    toolbarTitle(getString(R.string.Crime))
                    requireBinding().moviesRv.adapter = crimeAdapter
                }
                SeeAllMovieType.FANTASY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.FANTASY)
                    toolbarTitle(getString(R.string.fantasy))
                    requireBinding().moviesRv.adapter = fantasyAdapter
                }
                SeeAllMovieType.HORROR -> {
                    genresTryEmit(TvMoviesFragmentViewModel.HORROR)
                    toolbarTitle(getString(R.string.horror))
                    requireBinding().moviesRv.adapter = horrorAdapter
                }
                SeeAllMovieType.MUSIC -> {
                    genresTryEmit(TvMoviesFragmentViewModel.MUSIC)
                    toolbarTitle(getString(R.string.music))
                    requireBinding().moviesRv.adapter = musicAdapter
                }
                SeeAllMovieType.ROMANCE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ROMANCE)
                    toolbarTitle(getString(R.string.romance))
                    requireBinding().moviesRv.adapter = romanceAdapter
                }
                SeeAllMovieType.SCIENCEFICTION -> {
                    genresTryEmit(TvMoviesFragmentViewModel.SCIENCEFICTION)
                    toolbarTitle(getString(R.string.scienceFiction))
                    requireBinding().moviesRv.adapter = sciencefictionAdapter
                }
                SeeAllMovieType.TV_MOVIE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.TV_MOVIE)
                    toolbarTitle(getString(R.string.tvMovie))
                    requireBinding().moviesRv.adapter = tvAdapter
                }
                SeeAllMovieType.THRILLER -> {
                    genresTryEmit(TvMoviesFragmentViewModel.THRILLER)
                    toolbarTitle(getString(R.string.thriller))
                    requireBinding().moviesRv.adapter = thrillerAdapter
                }
                SeeAllMovieType.WAR -> {
                    genresTryEmit(TvMoviesFragmentViewModel.WAR)
                    toolbarTitle(getString(R.string.war))
                    requireBinding().moviesRv.adapter = warAdapter
                }

            }
            error.onEach { makeToast(it, requireContext()) }
            launchWhenViewResumed { movieResponseState.observe(::observeResponseState) }
        }
    }

    private fun observeTopRated() = launchWhenViewStarted {
        viewModel.topRatedMovies.observe {
            topRatedAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = topRatedAdapter
        toolbarTitle(getString(R.string.top_rated_category))
    }

    private fun observeNowPlaying() = launchWhenViewStarted {
        viewModel.nowPlayingMovies.observe {
            nowPlayingAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = nowPlayingAdapter
        toolbarTitle(getString(R.string.now_playing_category))
    }

    private fun observeUpcoming() = launchWhenViewStarted {
        viewModel.upcomingMovies.observe {
            uiVisibility()
            upcomingAdapter.moviesList = it.movies
        }
        requireBinding().moviesRv.adapter = upcomingAdapter
        toolbarTitle(getString(R.string.adventure_text))
    }

    private fun observePopular() = launchWhenViewStarted {
        viewModel.popularMovies.observe {
            uiVisibility()
            popularAdapter.moviesList = it.movies
        }
        requireBinding().moviesRv.adapter = popularAdapter
        toolbarTitle(getString(R.string.popular_category))
    }

    private fun observeTrending() = launchWhenViewStarted {
        viewModel.trendingMovies.observe {
            trendingMoviesAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = trendingMoviesAdapter
        toolbarTitle(getString(R.string.trending_category))
    }

    private fun uiVisibility() = with(requireBinding()) {
        lifecycleScope.launch {
            pageConstraint.showView()
            delay(400L)
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
        viewModel.launchFromMovieTypeToDetails(movieId = item.movieId)
    }


    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }

    private fun toolbarTitle(name: String) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = name
    }

}