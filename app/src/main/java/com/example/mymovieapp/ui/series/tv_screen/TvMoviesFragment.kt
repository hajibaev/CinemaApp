package com.example.mymovieapp.ui.series.tv_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentTvMoviesBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.type.SeeAllTvType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        observeViewModel()
        setupClickers()
        setUi()
    }

    private fun setAdapters() = with(requireBinding()) {
        trending.rv.adapter = trendingRv
        topRated.rv.adapter = topRatedRv
        onTheAir.rv.adapter = onTheAirRv
        popular.rv.adapter = popularRv
        airingToday.rv.adapter = airingTodayRv
    }

    private fun setUi() = with(requireBinding()) {
        trending.title.text = getString(R.string.tv_trending_text)
        topRated.title.text = getString(R.string.tv_topreted_text)
        onTheAir.title.text = getString(R.string.tv_on_the_air_text)
        popular.title.text = getString(R.string.tv_popular_text)
        airingToday.title.text = getString(R.string.tv_airing_today_rv)
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
        }
        error.onEach {
            makeToast(it, requireContext())
        }
    }


    private fun uiVisibility() = with(requireBinding()) {
        shimmerLayout.hideView()
        itemMovie.showView()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            trending.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.TRENDING) }
            topRated.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.TOP_RATED) }
            onTheAir.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.ONTHEAIR) }
            popular.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.POPULAR) }
            airingToday.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.AIRINGTODAY) }
        }
    }

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.launchTvDetails(seriesUi.id)
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

