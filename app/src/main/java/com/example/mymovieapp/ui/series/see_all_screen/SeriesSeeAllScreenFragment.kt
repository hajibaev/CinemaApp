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
    }

    private fun observeType(tv: SeeAllTvType) = with(requireBinding()) {
        when (tv) {
            SeeAllTvType.TOP_RATED -> observeTopRatedRv()
            SeeAllTvType.AIRINGTODAY -> observeAiringTodayRv()
            SeeAllTvType.ONTHEAIR -> observeOnTheAirRv()
            SeeAllTvType.POPULAR -> observePopularRv()
            SeeAllTvType.TRENDING -> observeTrendingRv()
            SeeAllTvType.FAMILYTYPE -> observeFamilyRv()
            SeeAllTvType.ANIMETYPE -> observeAnimeRv()
            SeeAllTvType.DRAMATYPE -> observeDramaRv()
            SeeAllTvType.COMEDY -> observeComedyRv()
            SeeAllTvType.HISTORY -> observeHistoryRv()
            SeeAllTvType.WESTERN -> observeWesternRv()
            SeeAllTvType.MYSTERY -> observeMysteryRv()
            SeeAllTvType.CRIME -> observeCrimeRv()
            SeeAllTvType.ACTION -> observeActionRv()
            SeeAllTvType.DOCUMENTARY -> observeDocumentaryRv()
            SeeAllTvType.KIDS -> observeKidsRv()
            SeeAllTvType.NEWS -> observeNewsRv()
            SeeAllTvType.REALITY -> observeRealityRv()
            SeeAllTvType.FANTASY -> observeFantasyRv()
            SeeAllTvType.SOAP -> observeSoapRv()
            SeeAllTvType.TALK -> observeTalkRv()
            SeeAllTvType.POLITICS -> observePoliticsRv()

        }
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
        launchWhenViewResumed { viewModel.movieResponseState.observe(::observeResponseState) }
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

    private fun observePoliticsRv() = launchWhenViewResumed {
        viewModel.politicsMovies.observe {
            politicsAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.politics))
        requireBinding().moviesRv.adapter = politicsAdapter
    }

    private fun observeTalkRv() = launchWhenViewResumed {
        viewModel.talkMovies.observe {
            talkAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.talk))
        requireBinding().moviesRv.adapter = talkAdapter
    }

    private fun observeSoapRv() = launchWhenViewResumed {
        viewModel.soapMovies.observe {
            soapAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.soap))
        requireBinding().moviesRv.adapter = soapAdapter
    }

    private fun observeFantasyRv() = launchWhenViewResumed {
        viewModel.fantasyMovies.observe {
            fantasyAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.fantasy))
        requireBinding().moviesRv.adapter = fantasyAdapter
    }

    private fun observeRealityRv() = launchWhenViewResumed {
        viewModel.realityMovies.observe {
            realityAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.reality))
        requireBinding().moviesRv.adapter = realityAdapter
    }

    private fun observeNewsRv() = launchWhenViewResumed {
        viewModel.newsMovies.observe {
            newsAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.news))
        requireBinding().moviesRv.adapter = newsAdapter
    }

    private fun observeKidsRv() = launchWhenViewResumed {
        viewModel.kidsMovies.observe {
            kidsAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.kids))
        requireBinding().moviesRv.adapter = kidsAdapter
    }

    private fun observeDocumentaryRv() = launchWhenViewResumed {
        viewModel.documentaryMovies.observe {
            documentaryAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.documentary))
        requireBinding().moviesRv.adapter = documentaryAdapter
    }

    private fun observeActionRv() = launchWhenViewResumed {
        viewModel.actionMovies.observe {
            actionAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.action))
        requireBinding().moviesRv.adapter = actionAdapter
    }

    private fun observeCrimeRv() = launchWhenViewResumed {
        viewModel.crimeMovies.observe {
            crimeMoviesAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.Crime))
        requireBinding().moviesRv.adapter = crimeMoviesAdapter
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

    private fun observeFamilyRv() = launchWhenViewResumed {
        viewModel.familyMovies.observe {
            familyAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.family_text))
        requireBinding().moviesRv.adapter = familyAdapter
    }


    private fun observeAnimeRv() = launchWhenViewResumed {
        viewModel.anime.observe {
            animeAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.animation))
        requireBinding().moviesRv.adapter = animeAdapter
    }


    private fun observeDramaRv() = launchWhenViewResumed {
        viewModel.dramaMovies.observe {
            dramaAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.drama))
        requireBinding().moviesRv.adapter = dramaAdapter
    }

    private fun observeComedyRv() = launchWhenViewResumed {
        viewModel.comedyMovies.observe {
            comedyAdapter.moviesList = it.results
        }
        toolbarTitle(getString(R.string.comedy))
        requireBinding().moviesRv.adapter = comedyAdapter
    }

    private fun observeHistoryRv() = launchWhenViewResumed {
        viewModel.historyMovies.observe {
            historyAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.history))
        requireBinding().moviesRv.adapter = historyAdapter
    }

    private fun observeWesternRv() = launchWhenViewResumed {
        viewModel.westernMovies.observe {
            westernAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.western))
        requireBinding().moviesRv.adapter = westernAdapter
    }

    private fun observeMysteryRv() = launchWhenViewResumed {
        viewModel.mysteryMovies.observe {
            mysteryAdapter.moviesList = it.results
            screenVisibility()
        }
        toolbarTitle(getString(R.string.mystery))
        requireBinding().moviesRv.adapter = mysteryAdapter
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

    override fun onItemClick(item: SeriesUi) = viewModel.launchFromTvTypeToDetails(item)

    private fun toolbarTitle(name: String) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = name
    }

    override fun onLongClick(item: SeriesUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.originalName} saved")
    }
}