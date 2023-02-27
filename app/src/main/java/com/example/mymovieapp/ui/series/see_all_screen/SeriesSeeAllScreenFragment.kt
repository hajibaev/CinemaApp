package com.example.mymovieapp.ui.series.see_all_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentTvTypeBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.series.tv_screen.TvMoviesFragmentViewModel
import com.example.mymovieapp.ui.type.SeeAllTvType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesSeeAllScreenFragment : BaseFragment<FragmentTvTypeBinding, TvMoviesFragmentViewModel>(
    FragmentTvTypeBinding::inflate
), RvClickListener<SeriesUi> {

    private val args by navArgs<SeriesSeeAllScreenFragmentArgs>()
    override val viewModel: TvMoviesFragmentViewModel by viewModels()

    private val trendingRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val airingTodayRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val familyAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val dramaAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val animeAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val comedyAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val historyAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val mysteryAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val westernAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val crimeMoviesAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val actionAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val documentaryAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val kidsAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val newsAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val realityAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val fantasyAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val soapAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val talkAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val politicsAdapter by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType(args.tvType)
        adapterAnimation()
        setupClickers()
        observe()
    }

    private fun observe() = with(viewModel) {
        launchWhenViewStarted {
            allGenres.observe {
                familyAdapter.moviesList = it.results
                dramaAdapter.moviesList = it.results
                animeAdapter.moviesList = it.results
                comedyAdapter.moviesList = it.results
                historyAdapter.moviesList = it.results
                mysteryAdapter.moviesList = it.results
                westernAdapter.moviesList = it.results
                crimeMoviesAdapter.moviesList = it.results
                actionAdapter.moviesList = it.results
                documentaryAdapter.moviesList = it.results
                kidsAdapter.moviesList = it.results
                newsAdapter.moviesList = it.results
                realityAdapter.moviesList = it.results
                fantasyAdapter.moviesList = it.results
                soapAdapter.moviesList = it.results
                talkAdapter.moviesList = it.results
                politicsAdapter.moviesList = it.results
                screenVisibility()
            }
        }
    }

    private fun observeType(tv: SeeAllTvType) = with(requireBinding()) {
        with(viewModel) {
            when (tv) {
                SeeAllTvType.TOP_RATED -> observeTopRatedRv()
                SeeAllTvType.AIRINGTODAY -> observeAiringTodayRv()
                SeeAllTvType.ONTHEAIR -> observeOnTheAirRv()
                SeeAllTvType.POPULAR -> observePopularRv()
                SeeAllTvType.TRENDING -> observeTrendingRv()
                SeeAllTvType.FAMILYTYPE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.FAMILY)
                    toolbarTitle(getString(R.string.family_text))
                    moviesRv.adapter = familyAdapter
                }
                SeeAllTvType.ANIMETYPE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ANIMATION)
                    toolbarTitle(getString(R.string.animation))
                    moviesRv.adapter = animeAdapter
                }
                SeeAllTvType.DRAMATYPE -> {
                    genresTryEmit(TvMoviesFragmentViewModel.DRAMA)
                    toolbarTitle(getString(R.string.drama))
                    moviesRv.adapter = dramaAdapter
                }
                SeeAllTvType.COMEDY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.COMEDY)
                    toolbarTitle(getString(R.string.comedy))
                    moviesRv.adapter = comedyAdapter
                }
                SeeAllTvType.HISTORY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.HISTORY)
                    toolbarTitle(getString(R.string.history))
                    moviesRv.adapter = historyAdapter
                }
                SeeAllTvType.WESTERN -> {
                    genresTryEmit(TvMoviesFragmentViewModel.WESTERN)
                    toolbarTitle(getString(R.string.western))
                    moviesRv.adapter = westernAdapter
                }
                SeeAllTvType.MYSTERY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.MYSTERY)
                    toolbarTitle(getString(R.string.mystery))
                    moviesRv.adapter = mysteryAdapter
                }
                SeeAllTvType.CRIME -> {
                    genresTryEmit(TvMoviesFragmentViewModel.CRIME)
                    toolbarTitle(getString(R.string.Crime))
                    moviesRv.adapter = crimeMoviesAdapter
                }
                SeeAllTvType.ACTION -> {
                    genresTryEmit(TvMoviesFragmentViewModel.ACTIONTV)
                    toolbarTitle(getString(R.string.action))
                    moviesRv.adapter = actionAdapter
                }
                SeeAllTvType.DOCUMENTARY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.DOCUMENTARY)
                    toolbarTitle(getString(R.string.documentary))
                    moviesRv.adapter = documentaryAdapter
                }
                SeeAllTvType.KIDS -> {
                    genresTryEmit(TvMoviesFragmentViewModel.KIDS)
                    toolbarTitle(getString(R.string.kids))
                    moviesRv.adapter = kidsAdapter
                }
                SeeAllTvType.NEWS -> {
                    genresTryEmit(TvMoviesFragmentViewModel.NEWS)
                    toolbarTitle(getString(R.string.news))
                    moviesRv.adapter = newsAdapter
                }
                SeeAllTvType.REALITY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.REALITY)
                    toolbarTitle(getString(R.string.reality))
                    moviesRv.adapter = realityAdapter
                }
                SeeAllTvType.FANTASY -> {
                    genresTryEmit(TvMoviesFragmentViewModel.FANTASYTV)
                    toolbarTitle(getString(R.string.fantasy))
                    moviesRv.adapter = fantasyAdapter
                }
                SeeAllTvType.SOAP -> {
                    genresTryEmit(TvMoviesFragmentViewModel.SOAP)
                    toolbarTitle(getString(R.string.soap))
                    moviesRv.adapter = soapAdapter
                }
                SeeAllTvType.TALK -> {
                    genresTryEmit(TvMoviesFragmentViewModel.TALK)
                    toolbarTitle(getString(R.string.talk))
                    moviesRv.adapter = talkAdapter
                }
                SeeAllTvType.POLITICS -> {
                    genresTryEmit(TvMoviesFragmentViewModel.POLITICS)
                    toolbarTitle(getString(R.string.politics))
                    moviesRv.adapter = politicsAdapter
                }
            }
            viewModel.error.onEach {
                makeToast(it, requireContext())
            }
            launchWhenViewResumed { viewModel.movieResponseState.observe(::observeResponseState) }
        }
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

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClick { viewModel.navigateBack() }
        viewModel.apply {
            nextBtn.setOnDownEffectClick {
                nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnDownEffectClick {
                previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun observeTopRatedRv() = launchWhenViewResumed {
        viewModel.tvTopRated.observe {
            topRatedRv.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.tv_topreted_text))
        requireBinding().moviesRv.adapter = topRatedRv
    }

    private fun observeAiringTodayRv() = launchWhenViewResumed {
        viewModel.tvAiringToday.observe {
            airingTodayRv.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.tv_airing_today_rv))
        requireBinding().moviesRv.adapter = airingTodayRv
    }

    private fun observeOnTheAirRv() = launchWhenViewResumed {
        viewModel.tvOnTheAir.observe {
            onTheAirRv.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.tv_on_the_air_text))
        requireBinding().moviesRv.adapter = onTheAirRv
    }

    private fun observePopularRv() = launchWhenViewResumed {
        viewModel.tvPopular.observe {
            popularRv.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.tv_popular_text))
        requireBinding().moviesRv.adapter = popularRv
    }

    private fun observeTrendingRv() = launchWhenViewResumed {
        viewModel.tvTrending.observe {
            trendingRv.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.tv_trending_text))
        requireBinding().moviesRv.adapter = trendingRv
    }


    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    private fun screenVisibility() = with(requireBinding()) {
        lifecycleScope.launch {
            delay(400L)
            pageConstraint.showView()
        }
        isEmptyLoading.hideView()
    }

    private fun adapterAnimation() = with(requireBinding()) {
        moviesRv.itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_portrait_item,
                SlideInTopCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onItemClick(item: SeriesUi) = viewModel.launchFromTvTypeToDetails(item.id)

    private fun toolbarTitle(name: String) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = name
    }

    override fun onLongClick(item: SeriesUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.originalName} saved")
    }
}