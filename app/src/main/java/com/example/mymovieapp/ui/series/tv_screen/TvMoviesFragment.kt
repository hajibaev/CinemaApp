package com.example.mymovieapp.ui.series.tv_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvMoviesBinding
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.ui.type.SeeAllTvType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TvMoviesFragment : BaseFragment<FragmentTvMoviesBinding, TvMoviesFragmentViewModel>(
    FragmentTvMoviesBinding::inflate
), RvClickListener<SeriesUi> {

    override val viewModel: TvMoviesFragmentViewModel by viewModels()

    private val trendingRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val airingTodayRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val familyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val dramaMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val animeMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val comedyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val historyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val mysteryMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val westernMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterToRv()
        observeViewModel()
        setupClickers()
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        tvTrendingRv.adapter = trendingRv
        tvTopretedRv.adapter = topRatedRv
        tvOnTheAirRv.adapter = onTheAirRv
        tvPopularRv.adapter = popularRv
        tvAiringTodayRv.adapter = airingTodayRv
        familyRV.adapter = familyMoviesAdapter
        animatoinRv.adapter = animeMoviesAdapter
        dramaRv.adapter = dramaMoviesAdapter
        comedyRv.adapter = comedyMoviesAdapter
        mysteryRv.adapter = mysteryMoviesAdapter
        westernRv.adapter = westernMoviesAdapter
        historyRv.adapter = historyMoviesAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            tvTrending.observe {
                trendingRv.moviesList = it.results
                uiVisibility()
            }
            tvTopRated.observe { topRatedRv.moviesList = it.results }
            tvOnTheAir.observe { onTheAirRv.moviesList = it.results }
            tvPopular.observe { popularRv.moviesList = it.results }
            tvAiringToday.observe { airingTodayRv.moviesList = it.results }
            familyMovies.observe { familyMoviesAdapter.moviesList = it.results }
            anime.observe { animeMoviesAdapter.moviesList = it.results }
            dramaMovies.observe { dramaMoviesAdapter.moviesList = it.results }
            comedyMovies.observe { comedyMoviesAdapter.moviesList = it.results }
            historyMovies.observe { historyMoviesAdapter.moviesList = it.results }
            westernMovies.observe { westernMoviesAdapter.moviesList = it.results }
            mysteryMovies.observe { mysteryMoviesAdapter.moviesList = it.results }

        }
        error.onEach {
            makeToast(it, requireContext())
        }
    }


    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = View.INVISIBLE
        shimmerLayout.hideView()
        constLayout.showView()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            upcomingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TRENDING) }
            nowplayingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TOP_RATED) }
            topText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ONTHEAIR) }
            popularText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.POPULAR) }
            trendingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.AIRINGTODAY) }
            familyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.FAMILYTYPE) }
            animeText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ANIMETYPE) }
            dramaText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.DRAMATYPE) }
            comedyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.COMEDY) }
            historyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.HISTORY) }
            mysteryText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.MYSTERY) }
            westernText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.WESTERN) }
        }
    }

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.launchTvDetails(seriesUi)
        Animatoo.animateSplit(requireActivity())

    }

    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onLongClick(item: SeriesUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.originalName} saved")
    }
}

