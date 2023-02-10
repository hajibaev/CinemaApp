package com.example.mymovieapp.ui.see_all_screen.fragments

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvTypeBinding
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.makeToast
import com.example.mymovieapp.ui.see_all_screen.SeeAllTvType
import com.example.mymovieapp.ui.tv_screen.tv_screen.TvMoviesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TvTypeFragment : BaseFragment<FragmentTvTypeBinding, TvMoviesFragmentViewModel>(
    FragmentTvTypeBinding::inflate
), TvAdapter.RecyclerOnClickListener {

    private val args by navArgs<TvTypeFragmentArgs>()
    override val viewModel: TvMoviesFragmentViewModel by viewModels()

    private val trendingRv by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val topRatedRv by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val onTheAirRv by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val popularRv by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val airingTodayRv by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val familyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val dramaMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val animeMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val comedyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val historyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val mysteryMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }
    private val westernMoviesAdapter by lazy {
        TvAdapter(TvAdapter.PORTRAIT_TYPE, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeType(args.tvType)
        setupClickers()
    }

    private fun observeType(tv: SeeAllTvType) = with(requireBinding()) {
        when (tv) {
            SeeAllTvType.TOP_RATED -> {
                observeTopRatedRv()
                moviesRv.adapter = topRatedRv
            }
            SeeAllTvType.AIRINGTODAY -> {
                observeAiringTodayRv()
                moviesRv.adapter = airingTodayRv
            }
            SeeAllTvType.ONTHEAIR -> {
                observeOnTheAirRv()
                moviesRv.adapter = onTheAirRv
            }
            SeeAllTvType.POPULAR -> {
                observePopularRv()
                moviesRv.adapter = popularRv
            }
            SeeAllTvType.TRENDING -> {
                observeTrendingRv()
                moviesRv.adapter = trendingRv
            }
            SeeAllTvType.FAMILYTYPE -> {
                observeFamilyRv()
                moviesRv.adapter = familyMoviesAdapter
            }
            SeeAllTvType.ANIMETYPE -> {
                observeAnimeRv()
                moviesRv.adapter = animeMoviesAdapter
            }
            SeeAllTvType.DRAMATYPE -> {
                observeDramaRv()
                moviesRv.adapter = dramaMoviesAdapter
            }
            SeeAllTvType.COMEDY -> {
                observeComedyRv()
                moviesRv.adapter = comedyMoviesAdapter
            }
            SeeAllTvType.HISTORY -> {
                observeHistoryRv()
                moviesRv.adapter = historyMoviesAdapter
            }
            SeeAllTvType.WESTERN -> {
                observeWesternRv()
                moviesRv.adapter = westernMoviesAdapter
            }
            SeeAllTvType.MYSTERY -> {
                observeMysteryRv()
                moviesRv.adapter = mysteryMoviesAdapter
            }
        }
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
        lifecycleScope.launchWhenResumed {
            viewModel.movieResponseState.collectLatest { state ->
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
        }
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            nextBtn.setOnClickListener {
                nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnClickListener {
                previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }


    private fun observeTopRatedRv() = lifecycleScope.launchWhenStarted {
        viewModel.tvTopRated.collectLatest {
            topRatedRv.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeAiringTodayRv() = lifecycleScope.launchWhenResumed {
        viewModel.tvAiringToday.collectLatest {
            airingTodayRv.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeOnTheAirRv() = lifecycleScope.launchWhenResumed {
        viewModel.tvOnTheAir.collectLatest {
            onTheAirRv.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observePopularRv() = lifecycleScope.launchWhenResumed {
        viewModel.tvPopular.collectLatest {
            popularRv.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeTrendingRv() = lifecycleScope.launchWhenResumed {
        viewModel.tvTrending.collectLatest {
            trendingRv.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeFamilyRv() = lifecycleScope.launchWhenResumed {
        viewModel.familyMovies.collectLatest {
            familyMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeAnimeRv() = lifecycleScope.launchWhenResumed {
        viewModel.anime.collectLatest {
            animeMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeDramaRv() = lifecycleScope.launchWhenResumed {
        viewModel.dramaMovies.collectLatest {
            dramaMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeComedyRv() = lifecycleScope.launchWhenResumed {
        viewModel.comedyMovies.collectLatest {
            comedyMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeHistoryRv() = lifecycleScope.launchWhenResumed {
        viewModel.historyMovies.collectLatest {
            historyMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeWesternRv() = lifecycleScope.launchWhenResumed {
        viewModel.westernMovies.collectLatest {
            westernMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    private fun observeMysteryRv() = lifecycleScope.launchWhenResumed {
        viewModel.mysteryMovies.collectLatest {
            mysteryMoviesAdapter.moviesList = it.results
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
        }
    }

    override fun onItemClick(movie: SeriesUi) = viewModel.launchFromTvTypeToDetails(movie)
    override fun onLongItemClick(movie: SeriesUi) {
        viewModel.saveMovie(movie)
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}