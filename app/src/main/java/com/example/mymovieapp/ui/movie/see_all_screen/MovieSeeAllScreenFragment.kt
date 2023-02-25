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
import com.example.mymovieapp.ui.see_more_sort_dialog.SettingsFragment
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
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClick { viewModel.navigateBack() }
//        sortOptions.setOnDownEffectClickListener { showSettingModalPage() }
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

    private fun observeType() = with(viewModel) {
        requireBinding().apply {
            when (args.movieType) {
                SeeAllMovieType.TOP_RATED -> observeRatingMovies()
                SeeAllMovieType.NOW_PLAYING -> observeRelevanceMovies()
                SeeAllMovieType.UPCOMING -> observePublishedAtMovies()
                SeeAllMovieType.POPULAR -> observePopularMovies()
                SeeAllMovieType.TRENDING -> observeTrendingMovies()
                SeeAllMovieType.DRAMA -> observeDramaMovies()
                SeeAllMovieType.FAMILY -> observeFamilyMovies()
                SeeAllMovieType.COMEDY -> observeComedyMovies()
                SeeAllMovieType.DOCUMENTARY -> observeDocumentaryMovies()
                SeeAllMovieType.HISTORY -> observeHistoryMovies()
                SeeAllMovieType.MYSTERY -> observeMysteryMovies()
                SeeAllMovieType.WESTERN -> observeWesternMovies()
                SeeAllMovieType.ACTION -> observeActionMovies()
                SeeAllMovieType.ADVENTURE -> observeAdventureMovies()
                SeeAllMovieType.ANIMATION -> observeAnimationMovies()
                SeeAllMovieType.CRIME -> observeCrimeMovies()
                SeeAllMovieType.FANTASY -> observeFantasyMovies()
                SeeAllMovieType.HORROR -> observeHorrorMovies()
                SeeAllMovieType.MUSIC -> observeMusicMovies()
                SeeAllMovieType.ROMANCE -> observeRomanceMovies()
                SeeAllMovieType.SCIENCEFICTION -> observeSciencefictionMovies()
                SeeAllMovieType.TV_MOVIE -> observeTvMovies()
                SeeAllMovieType.THRILLER -> observeThrillerMovies()
                SeeAllMovieType.WAR -> observeWarMovies()
            }
            error.onEach { makeToast(it, requireContext()) }
            launchWhenViewResumed { movieResponseState.observe(::observeResponseState) }
        }
    }

    private fun observePopularMovies() = launchWhenViewResumed {
        viewModel.popularMovies.observe {
            uiVisibility()
            popularAdapter.moviesList = it.movies
        }
        requireBinding().moviesRv.adapter = popularAdapter
        toolbarTitle(getString(R.string.popular_category))
    }

    private fun observeRatingMovies() = launchWhenViewResumed {
        viewModel.topRatedMovies.observe {
            uiVisibility()

            topRatedAdapter.moviesList = it.movies
        }
        requireBinding().moviesRv.adapter = topRatedAdapter
        toolbarTitle(getString(R.string.top_rated_category))
    }


    private fun observePublishedAtMovies() = launchWhenViewResumed {
        viewModel.upcomingMovies.observe {
            uiVisibility()
            upcomingAdapter.moviesList = it.movies
        }
        requireBinding().moviesRv.adapter = upcomingAdapter
        toolbarTitle(getString(R.string.adventure_text))
    }

    private fun observeAdventureMovies() = launchWhenViewResumed {
        viewModel.adventureMovies.observe {
            adventureAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.adventure))
        requireBinding().moviesRv.adapter = adventureAdapter
    }

    private fun observeDocumentaryMovies() = launchWhenViewResumed {
        viewModel.documentaryMovies.observe {
            documentaryAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.documentary))
        requireBinding().moviesRv.adapter = documentaryAdapter
    }

    private fun observeActionMovies() = launchWhenViewResumed {
        viewModel.actionMovies.observe {
            actionAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.action))
        requireBinding().moviesRv.adapter = actionAdapter
    }

    private fun observeCrimeMovies() = launchWhenViewResumed {
        viewModel.crimeMovies.observe {
            crimeAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.Crime))
        requireBinding().moviesRv.adapter = crimeAdapter
    }

    private fun observeHorrorMovies() = launchWhenViewResumed {
        viewModel.horrorMovies.observe {
            horrorAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.horror))
        requireBinding().moviesRv.adapter = horrorAdapter
    }

    private fun observeMusicMovies() = launchWhenViewResumed {
        viewModel.musicMovies.observe {
            musicAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.music))
        requireBinding().moviesRv.adapter = musicAdapter
    }

    private fun observeFantasyMovies() = launchWhenViewResumed {
        viewModel.fantasyMovies.observe {
            fantasyAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.fantasy))
        requireBinding().moviesRv.adapter = fantasyAdapter
    }

    private fun observeRomanceMovies() = launchWhenViewResumed {
        viewModel.romanceMovies.observe {
            romanceAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.romance))
        requireBinding().moviesRv.adapter = romanceAdapter
    }

    private fun observeSciencefictionMovies() = launchWhenViewResumed {
        viewModel.sciencefictionMovies.observe {
            sciencefictionAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.scienceFiction))
        requireBinding().moviesRv.adapter = sciencefictionAdapter
    }

    private fun observeTvMovies() = launchWhenViewResumed {
        viewModel.tvMovies.observe {
            tvAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.tvMovie))
        requireBinding().moviesRv.adapter = tvAdapter
    }

    private fun observeThrillerMovies() = launchWhenViewResumed {
        viewModel.thrillerMovies.observe {
            thrillerAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.thriller))
        requireBinding().moviesRv.adapter = thrillerAdapter
    }

    private fun observeWarMovies() = launchWhenViewResumed {
        viewModel.warMovies.observe {
            warAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.war))
        requireBinding().moviesRv.adapter = warAdapter
    }


    private fun observeRelevanceMovies() = launchWhenViewResumed {
        viewModel.nowPlayingMovies.observe {
            nowPlayingAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = nowPlayingAdapter
        toolbarTitle(getString(R.string.now_playing_category))
    }


    private fun observeTrendingMovies() = launchWhenViewResumed {
        viewModel.trendingMovies.observe {
            trendingMoviesAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = trendingMoviesAdapter
        toolbarTitle(getString(R.string.trending_category))
    }

    private fun observeDramaMovies() = launchWhenViewResumed {
        viewModel.dramaMovies.observe {
            dramaAdapter.moviesList = it.movies
            uiVisibility()
        }
        requireBinding().moviesRv.adapter = dramaAdapter
        toolbarTitle(getString(R.string.drama))
    }


    private fun observeFamilyMovies() = launchWhenViewResumed {
        viewModel.familyMovies.observe {
            familyAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.family_text))
        requireBinding().moviesRv.adapter = familyAdapter
    }

    private fun observeAnimationMovies() = launchWhenViewResumed {
        viewModel.animationMovies.observe {
            animationAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.animation))
        requireBinding().moviesRv.adapter = animationAdapter
    }

    private fun observeComedyMovies() = launchWhenViewResumed {
        viewModel.comedyMovies.observe {
            comedyAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.comedy))
        requireBinding().moviesRv.adapter = comedyAdapter
    }

    private fun observeHistoryMovies() = launchWhenViewResumed {
        viewModel.historyMovies.observe {
            historyAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.history))
        requireBinding().moviesRv.adapter = historyAdapter
    }

    private fun observeMysteryMovies() = launchWhenViewResumed {
        viewModel.mysteryMovies.observe {
            mysteryAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.mystery))
        requireBinding().moviesRv.adapter = mysteryAdapter
    }

    private fun observeWesternMovies() = launchWhenViewResumed {
        viewModel.westernMovies.observe {
            westernAdapter.moviesList = it.movies
            uiVisibility()
        }
        toolbarTitle(getString(R.string.western))
        requireBinding().moviesRv.adapter = westernAdapter
    }

    private fun uiVisibility() = with(requireBinding()) {
        isEmptyLoading.hideView()

//        lifecycleScope.launch {
//            delay(400L)
        pageConstraint.showView()
//        }

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
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view)
            .hideView()
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.launchFromMovieTypeToDetails(movie = item)
    }


    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }

    private fun showSettingModalPage() =
        SettingsFragment.newInstance(getString(R.string.setting), args.movieType)
            .show(requireActivity().supportFragmentManager, ModalPage.TAG)

    private fun toolbarTitle(name: String) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = name
    }

}