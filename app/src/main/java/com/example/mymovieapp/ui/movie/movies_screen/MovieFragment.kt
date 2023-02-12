package com.example.mymovieapp.ui.movie.movies_screen

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import androidx.fragment.app.viewModels
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.*
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.see_all_screen.SeeAllMovieType
import com.example.mymovieapp.utils.extensions.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModels>(FragmentMovieBinding::inflate),
    MovieAdapter.RecyclerOnClickListener {

    override val viewModel: MovieViewModels by viewModels()

    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val topAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val upcomingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val trendingMoviesAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popularText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.POPULAR) }
            nowplayingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.UPCOMING) }
            topText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.TOP_RATED) }
            upcomingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.NOW_PLAYING) }
            trendingText.setOnDownEffectClickListener { launchMoviesTypeFragment(SeeAllMovieType.TRENDING) }
        }
    }

    private fun setAdapter() = with(requireBinding()) {
        popularRv.adapter = moviesAdapter
        nowplayingRv.adapter = nowPlayingAdapter
        tvTopretedRv.adapter = upcomingAdapter
        topRv.adapter = topAdapter
        trendingRv.adapter = trendingMoviesAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            popularMovies.observe { moviesAdapter.moviesList = it.movies }
            ratingMovies.observe { topAdapter.moviesList = it.movies }
            publishedAtMovies.observe { upcomingAdapter.moviesList = it.movies }
            relevanceMovies.observe { nowPlayingAdapter.moviesList = it.movies }
            uiVisibility()
            trendingMovies.observe { trendingMoviesAdapter.moviesList = it.movies }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.hideView()
            requireBinding().constLayout.showView()
        }
    }

    //
//    private fun openDialogSheet(item: MovieUi) {
//        val bottomSheet = BottomSheetDialog(requireContext())
//        bottomSheet.setContentView(R.layout.details_item_dialog)
//        val posterImage = bottomSheet.findViewById<ImageView>(R.id.poster_image)
//        val overviewText = bottomSheet.findViewById<TextView>(R.id.overview_text)
//        val playButton = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
//        val titleText = bottomSheet.findViewById<TextView>(R.id.title_text)
//        val closeDetailBtn = bottomSheet.findViewById<ImageView>(R.id.close_detail_btn)
//        val ratingText = bottomSheet.findViewById<TextView>(R.id.rating_text)
//        closeDetailBtn?.setOnDownEffectClickListener { bottomSheet.dismiss() }
//        Picasso.get().load(Utils.IMAGE_PATH + item.posterPath).into(posterImage)
//        ratingText?.text = item.voteAverage.toString()
//        overviewText?.text = item.overview
//        titleText?.text = item.originalTitle
//        playButton?.setOnDownEffectClickListener {
//            viewModel.launchMovieDetails(item)
//            bottomSheet.dismiss()
//        }
//        bottomSheet.setCancelable(true)
//        bottomSheet.show()
//    }


    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = INVISIBLE
        shimmerLayout.stopShimmer()
        shimmerLayout.hideView()
        constLayout.showView()
    }

    override fun onItemClick(movie: MovieUi) = viewModel.launchMovieDetails(movie)

    override fun onLongItemClick(movie: MovieUi) {
        viewModel.saveMovie(movie = movie)
        makeToast("Фильм (${movie.movieTitle}) Сохранён", requireContext())
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}
