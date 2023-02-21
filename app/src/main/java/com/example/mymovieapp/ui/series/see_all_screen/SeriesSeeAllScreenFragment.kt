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
import com.example.mymovieapp.databinding.FragmentTvTypeBinding
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.extensions.*
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

    private fun observeTopRatedRv() = launchWhenViewResumed {
        viewModel.tvTopRated.observe {
            topRatedRv.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = topRatedRv
    }

    private fun observeAiringTodayRv() = launchWhenViewResumed {
        viewModel.tvAiringToday.observe {
            airingTodayRv.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = airingTodayRv
    }

    private fun observeOnTheAirRv() = launchWhenViewResumed {
        viewModel.tvOnTheAir.observe {
            onTheAirRv.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = onTheAirRv
    }

    private fun observePopularRv() = launchWhenViewResumed {
        viewModel.tvPopular.observe {
            popularRv.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = popularRv
    }

    private fun observeTrendingRv() = launchWhenViewResumed {
        viewModel.tvTrending.observe {
            trendingRv.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = trendingRv
    }

    private fun observeFamilyRv() = launchWhenViewResumed {
        viewModel.familyMovies.observe {
            familyAdapter.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = familyAdapter
    }


    private fun observeAnimeRv() = launchWhenViewResumed {
        viewModel.anime.observe {
            animeAdapter.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = animeAdapter
    }


    private fun observeDramaRv() = launchWhenViewResumed {
        viewModel.dramaMovies.observe {
            dramaAdapter.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = dramaAdapter
    }

    private fun observeComedyRv() = launchWhenViewResumed {
        viewModel.comedyMovies.observe {
            comedyAdapter.moviesList = it.results
        }
        requireBinding().moviesRv.adapter = comedyAdapter
    }

    private fun observeHistoryRv() = launchWhenViewResumed {
        viewModel.historyMovies.observe {
            historyAdapter.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = historyAdapter
    }

    private fun observeWesternRv() = launchWhenViewResumed {
        viewModel.westernMovies.observe {
            westernAdapter.moviesList = it.results
            screenVisibility()
        }
        requireBinding().moviesRv.adapter = westernAdapter
    }

    private fun observeMysteryRv() = launchWhenViewResumed {
        viewModel.mysteryMovies.observe {
            mysteryAdapter.moviesList = it.results
            screenVisibility()
        }
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

    override fun onItemClick(item: SeriesUi) {
        viewModel.launchFromTvTypeToDetails(item)
    }

    override fun onLongClick(item: SeriesUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.originalName} saved")
    }
}