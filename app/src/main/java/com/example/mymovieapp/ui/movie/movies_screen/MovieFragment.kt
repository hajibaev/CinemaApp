package com.example.mymovieapp.ui.movie.movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentMovieBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModels>(FragmentMovieBinding::inflate),
    RvClickListener<MovieUi> {
    override val viewModel: MovieViewModels by viewModels()

    private val popularAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val topRatedAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val upcomingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val trendingMoviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val dramaAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val crimeAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val animationAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val comedyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val historyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val mysteryAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val westernAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val actionAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val adventureAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val documentaryAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val familyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val fantasyAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val horrorAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val musicAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val romanceAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val sciencefictionAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val tvAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val thrillerAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val warAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
        setupClickers()
        setUi()
    }

    private fun setUi() = with(requireBinding()) {
        popular.title.text = getString(R.string.popular_category)
        upcoming.title.text = getString(R.string.adventure_text)
        topRated.title.text = getString(R.string.top_rated_category)
        nowPlaying.title.text = getString(R.string.now_playing_category)
        trending.title.text = getString(R.string.trending_category)
        drama.title.text = getString(R.string.drama)
        family.title.text = getString(R.string.family_text)
        comedy.title.text = getString(R.string.comedy)
        documentary.title.text = getString(R.string.documentary)
        history.title.text = getString(R.string.history)
        mystery.title.text = getString(R.string.mystery)
        western.title.text = getString(R.string.western)
        action.title.text = getString(R.string.action)
        adventure.title.text = getString(R.string.adventure)
        fantasy.title.text = getString(R.string.fantasy)
        horror.title.text = getString(R.string.horror)
        music.title.text = getString(R.string.music)
        romance.title.text = getString(R.string.romance)
        sciencefiction.title.text = getString(R.string.scienceFiction)
        tv.title.text = getString(R.string.tvMovie)
        thriller.title.text = getString(R.string.thriller)
        war.title.text = getString(R.string.war)
        crime.title.text = getString(R.string.Crime)
        animation.title.text = getString(R.string.animation)
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popular.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.POPULAR) }
            upcoming.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.UPCOMING) }
            topRated.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.TOP_RATED) }
            nowPlaying.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.NOW_PLAYING) }
            trending.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.TRENDING) }
            drama.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.DRAMA) }
            family.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.FAMILY) }
            comedy.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.COMEDY) }
            documentary.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.DOCUMENTARY) }
            history.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.HISTORY) }
            mystery.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.MYSTERY) }
            western.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.WESTERN) }
            action.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.ACTION) }
            adventure.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.ADVENTURE) }
            fantasy.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.FANTASY) }
            horror.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.HORROR) }
            music.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.MUSIC) }
            romance.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.ROMANCE) }
            sciencefiction.seeMore.setOnDownEffectClick {
                launchMoviesTypeFragment(
                    SeeAllMovieType.SCIENCEFICTION
                )
            }
            tv.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.TV_MOVIE) }
            thriller.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.THRILLER) }
            war.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.WAR) }
            crime.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.CRIME) }
            animation.seeMore.setOnDownEffectClick { launchMoviesTypeFragment(SeeAllMovieType.ANIMATION) }
        }
    }

    private fun setAdapter() = with(requireBinding()) {
        popular.rv.adapter = popularAdapter
        nowPlaying.rv.adapter = nowPlayingAdapter
        topRated.rv.adapter = topRatedAdapter
        upcoming.rv.adapter = upcomingAdapter
        trending.rv.adapter = trendingMoviesAdapter
        drama.rv.adapter = dramaAdapter
        crime.rv.adapter = crimeAdapter
        animation.rv.adapter = animationAdapter
        comedy.rv.adapter = comedyAdapter
        history.rv.adapter = historyAdapter
        mystery.rv.adapter = mysteryAdapter
        western.rv.adapter = westernAdapter
        action.rv.adapter = actionAdapter
        adventure.rv.adapter = adventureAdapter
        documentary.rv.adapter = documentaryAdapter
        family.rv.adapter = familyAdapter
        fantasy.rv.adapter = fantasyAdapter
        horror.rv.adapter = horrorAdapter
        music.rv.adapter = musicAdapter
        romance.rv.adapter = romanceAdapter
        sciencefiction.rv.adapter = sciencefictionAdapter
        tv.rv.adapter = tvAdapter
        thriller.rv.adapter = thrillerAdapter
        war.rv.adapter = warAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            popularMovies.observe { popularAdapter.moviesList = it.movies }
            topRatedMovies.observe { topRatedAdapter.moviesList = it.movies }
            nowPlayingMovies.observe { nowPlayingAdapter.moviesList = it.movies }
            upcomingMovies.observe { upcomingAdapter.moviesList = it.movies }
            trendingMovies.observe {
                trendingMoviesAdapter.moviesList = it.movies
                uiVisibility()
            }
            dramaMovies.observe { dramaAdapter.moviesList = it.movies }
            crimeMovies.observe { crimeAdapter.moviesList = it.movies }
            animationMovies.observe { animationAdapter.moviesList = it.movies }
            comedyMovies.observe { comedyAdapter.moviesList = it.movies }
            historyMovies.observe { historyAdapter.moviesList = it.movies }
            mysteryMovies.observe { mysteryAdapter.moviesList = it.movies }
            westernMovies.observe { westernAdapter.moviesList = it.movies }
            actionMovies.observe { actionAdapter.moviesList = it.movies }
            adventureMovies.observe { adventureAdapter.moviesList = it.movies }
            documentaryMovies.observe { documentaryAdapter.moviesList = it.movies }
            familyMovies.observe { familyAdapter.moviesList = it.movies }
            fantasyMovies.observe { fantasyAdapter.moviesList = it.movies }
            horrorMovies.observe { horrorAdapter.moviesList = it.movies }
            musicMovies.observe { musicAdapter.moviesList = it.movies }
            romanceMovies.observe { romanceAdapter.moviesList = it.movies }
            sciencefictionMovies.observe { sciencefictionAdapter.moviesList = it.movies }
            tvMovies.observe { tvAdapter.moviesList = it.movies }
            thrillerMovies.observe { thrillerAdapter.moviesList = it.movies }
            warMovies.observe { warAdapter.moviesList = it.movies }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().apply {
                shimmerLayout.stopShimmer()
                shimmerLayout.hideView()
                itemMovie.showView()
            }
        }
    }

    private fun uiVisibility() = with(requireBinding()) {
        shimmerLayout.stopShimmer()
        shimmerLayout.hideView()
        itemMovie.showView()
    }

    override fun onItemClick(movie: MovieUi)  = viewModel.launchMovieDetails(movie)


    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view)
            .showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}
