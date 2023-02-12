package com.example.mymovieapp.ui.see_all_screen.fragments

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvTypeBinding
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.*
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.see_all_screen.SeeAllTvType
import com.example.mymovieapp.ui.tv_screen.tv_screen.TvMoviesFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TvTypeFragment : BaseFragment<FragmentTvTypeBinding, TvMoviesFragmentViewModel>(
    FragmentTvTypeBinding::inflate
), TvAdapter.RecyclerOnClickListener {

    private val args by navArgs<TvTypeFragmentArgs>()
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

    private fun observeTopRatedRv() = with(viewModel) {
        launchWhenViewResumed { tvTopRated.observe { topRatedRv.moviesList = it.results } }
        requireBinding().moviesRv.adapter = topRatedRv
        screenVisibility()
    }

    private fun observeAiringTodayRv() = with(viewModel) {
        launchWhenViewResumed { tvAiringToday.observe { airingTodayRv.moviesList = it.results } }
        requireBinding().moviesRv.adapter = airingTodayRv
        screenVisibility()
    }

    private fun observeOnTheAirRv() = with(viewModel) {
        launchWhenViewResumed { tvOnTheAir.observe { onTheAirRv.moviesList = it.results } }
        requireBinding().moviesRv.adapter = onTheAirRv
        screenVisibility()
    }

    private fun observePopularRv() = with(viewModel) {
        launchWhenViewResumed { tvPopular.observe { popularRv.moviesList = it.results } }
        requireBinding().moviesRv.adapter = popularRv
        screenVisibility()
    }

    private fun observeTrendingRv() = with(viewModel) {
        launchWhenViewResumed { tvTrending.observe { trendingRv.moviesList = it.results } }
        requireBinding().moviesRv.adapter = trendingRv
        screenVisibility()
    }

    private fun observeFamilyRv() = with(viewModel) {
        launchWhenViewResumed { familyMovies.observe { familyAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = familyAdapter
        screenVisibility()
    }

    private fun observeAnimeRv() = with(viewModel) {
        launchWhenViewResumed { anime.observe { animeAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = animeAdapter
        screenVisibility()
    }

    private fun observeDramaRv() = with(viewModel) {
        launchWhenViewResumed { dramaMovies.observe { dramaAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = dramaAdapter
        screenVisibility()
    }

    private fun observeComedyRv() = with(viewModel) {
        launchWhenViewResumed { comedyMovies.observe { comedyAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = comedyAdapter
        screenVisibility()
    }

    private fun observeHistoryRv() = with(viewModel) {
        launchWhenViewResumed { historyMovies.observe { historyAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = historyAdapter
        screenVisibility()
    }

    private fun observeWesternRv() = with(viewModel) {
        launchWhenViewResumed { westernMovies.observe { westernAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = westernAdapter
        screenVisibility()
    }

    private fun observeMysteryRv() = with(viewModel) {
        launchWhenViewResumed { mysteryMovies.observe { mysteryAdapter.moviesList = it.results } }
        requireBinding().moviesRv.adapter = mysteryAdapter
        screenVisibility()
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onItemClick(movie: SeriesUi) = viewModel.launchFromTvTypeToDetails(movie)

    override fun onLongItemClick(movie: SeriesUi) {
        viewModel.saveMovie(movie)
        makeToast("Фильм (${movie.originalName}) Сохранён", requireContext())
    }

    private fun screenVisibility() = with(requireBinding()) {
        pageConstraint.visibility = View.VISIBLE
        isEmptyLoading.visibility = View.GONE
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}