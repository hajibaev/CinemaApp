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
    private val crimeMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val dramaMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val animeMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val comedyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val historyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val mysteryMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val westernMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val actionAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val documentaryAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val familyAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val kidsAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val newsAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val realityAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val fantasyAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val soapAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val talkAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val politicsAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

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
        crime.rv.adapter = crimeMoviesAdapter
        animation.rv.adapter = animeMoviesAdapter
        drama.rv.adapter = dramaMoviesAdapter
        comedy.rv.adapter = comedyMoviesAdapter
        mystery.rv.adapter = mysteryMoviesAdapter
        western.rv.adapter = westernMoviesAdapter
        history.rv.adapter = historyMoviesAdapter
        action.rv.adapter = actionAdapter
        documentary.rv.adapter = documentaryAdapter
        family.rv.adapter = familyAdapter
        kids.rv.adapter = kidsAdapter
        news.rv.adapter = newsAdapter
        reality.rv.adapter = realityAdapter
        fantasy.rv.adapter = fantasyAdapter
        soap.rv.adapter = soapAdapter
        talk.rv.adapter = talkAdapter
        politics.rv.adapter = politicsAdapter
    }

    private fun setUi() = with(requireBinding()) {
        trending.title.text = getString(R.string.tv_trending_text)
        topRated.title.text = getString(R.string.tv_topreted_text)
        onTheAir.title.text = getString(R.string.tv_on_the_air_text)
        popular.title.text = getString(R.string.tv_popular_text)
        airingToday.title.text = getString(R.string.tv_airing_today_rv)
        crime.title.text = getString(R.string.Crime)
        animation.title.text = getString(R.string.animation)
        drama.title.text = getString(R.string.drama)
        comedy.title.text = getString(R.string.comedy)
        mystery.title.text = getString(R.string.mystery)
        western.title.text = getString(R.string.western)
        history.title.text = getString(R.string.history)
        action.title.text = getString(R.string.action)
        documentary.title.text = getString(R.string.documentary)
        family.title.text = getString(R.string.family_text)
        kids.title.text = getString(R.string.kids)
        news.title.text = getString(R.string.news)
        reality.title.text = getString(R.string.reality)
        fantasy.title.text = getString(R.string.fantasy)
        soap.title.text = getString(R.string.soap)
        talk.title.text = getString(R.string.talk)
        politics.title.text = getString(R.string.politics)
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
            crimeMovies.observe { crimeMoviesAdapter.moviesList = it.results }
            anime.observe { animeMoviesAdapter.moviesList = it.results }
            dramaMovies.observe { dramaMoviesAdapter.moviesList = it.results }
            comedyMovies.observe { comedyMoviesAdapter.moviesList = it.results }
            historyMovies.observe { historyMoviesAdapter.moviesList = it.results }
            westernMovies.observe { westernMoviesAdapter.moviesList = it.results }
            mysteryMovies.observe { mysteryMoviesAdapter.moviesList = it.results }
            actionMovies.observe { actionAdapter.moviesList = it.results }
            documentaryMovies.observe { documentaryAdapter.moviesList = it.results }
            familyMovies.observe { familyAdapter.moviesList = it.results }
            kidsMovies.observe { kidsAdapter.moviesList = it.results }
            newsMovies.observe { newsAdapter.moviesList = it.results }
            realityMovies.observe { realityAdapter.moviesList = it.results }
            fantasyMovies.observe { fantasyAdapter.moviesList = it.results }
            soapMovies.observe { soapAdapter.moviesList = it.results }
            talkMovies.observe { talkAdapter.moviesList = it.results }
            politicsMovies.observe { politicsAdapter.moviesList = it.results }

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
            family.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.FAMILYTYPE) }
            animation.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.ANIMETYPE) }
            drama.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.DRAMATYPE) }
            comedy.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.COMEDY) }
            history.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.HISTORY) }
            mystery.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.MYSTERY) }
            western.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.WESTERN) }
            crime.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.CRIME) }
            action.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.ACTION) }
            documentary.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.DOCUMENTARY) }
            kids.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.KIDS) }
            news.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.NEWS) }
            reality.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.REALITY) }
            fantasy.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.FANTASY) }
            soap.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.SOAP) }
            talk.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.TALK) }
            politics.seeMore.setOnDownEffectClick { launchTvType(SeeAllTvType.POLITICS) }
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

