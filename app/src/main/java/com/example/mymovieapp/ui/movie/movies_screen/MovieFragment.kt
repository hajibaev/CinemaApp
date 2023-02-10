package com.example.mymovieapp.ui.movie.movies_screen

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.hideView
import com.example.mymovieapp.ui.makeToast
import com.example.mymovieapp.ui.see_all_screen.SeeAllMovieType
import com.example.mymovieapp.ui.showView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MovieFragment :
    BaseFragment<FragmentMovieBinding, MovieViewModels>(FragmentMovieBinding::inflate),
    MovieAdapter.RecyclerOnClickListener {

    override val viewModel: MovieViewModels by viewModels()

    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val topAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val upcomingAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val nowPlayingAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val trendingMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }

    private var isFavorite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
        setupClickers()
    }


    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            popularText.setOnClickListener { launchMoviesTypeFragment(SeeAllMovieType.POPULAR) }
            nowplayingText.setOnClickListener { launchMoviesTypeFragment(SeeAllMovieType.UPCOMING) }
            topText.setOnClickListener { launchMoviesTypeFragment(SeeAllMovieType.TOP_RATED) }
            upcomingText.setOnClickListener { launchMoviesTypeFragment(SeeAllMovieType.NOW_PLAYING) }
            trendingText.setOnClickListener { launchMoviesTypeFragment(SeeAllMovieType.TRENDING) }
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
        lifecycleScope.launchWhenResumed {
            popularMovies.collectLatest {
                moviesAdapter.moviesList = it.movies
            }
        }
        lifecycleScope.launchWhenResumed {
            ratingMovies.collectLatest {
                topAdapter.moviesList = it.movies
                uiVisibility()
            }
        }
        lifecycleScope.launchWhenResumed {
            publishedAtMovies.collectLatest {
                upcomingAdapter.moviesList = it.movies
            }
        }
        lifecycleScope.launchWhenResumed {
            relevanceMovies.collectLatest {
                nowPlayingAdapter.moviesList = it.movies
            }
        }
        lifecycleScope.launchWhenResumed {
            trendingMovies.collectLatest {
                trendingMoviesAdapter.moviesList = it.movies
            }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.hideView()
            requireBinding().constLayout.showView()
        }
    }


    private fun openDialogSheet(item: MovieUi) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.details_item_dialog)
        val movieImage = bottomSheet.findViewById<ImageView>(R.id.poster_image)
        val movieDes = bottomSheet.findViewById<TextView>(R.id.overview_text)
        val movieMore = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
        val movieTitle = bottomSheet.findViewById<TextView>(R.id.title_text)
        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_detail_btn)
        val add = bottomSheet.findViewById<ImageView>(R.id.add)
        val rating = bottomSheet.findViewById<TextView>(R.id.rating_text)
        cancelBtn?.setOnClickListener {
            bottomSheet.dismiss()
        }
        if (isFavorite) add?.setImageResource(R.drawable.ic_saved)
        else add?.setImageResource(R.drawable.ic_save_vector)
        Picasso.get().load(Utils.IMAGE_PATH + item.posterPath).into(movieImage)
        rating?.text = item.voteAverage.toString()
        movieDes?.text = item.overview
        movieTitle?.text = item.originalTitle
        add?.setOnClickListener {
            if (isFavorite) {
                isFavorite = false
                add.setImageResource(R.drawable.ic_save_vector)
                viewModel.deleteMovie(item.movieId)
            } else {
                isFavorite = true
                add.setImageResource(R.drawable.ic_saved)
                viewModel.saveMovie(item)
            }
        }
        movieMore?.setOnClickListener {
            viewModel.launchMovieDetails(item)
            bottomSheet.dismiss()
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()
    }

    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = INVISIBLE
        textView.visibility = VISIBLE
        textView2.visibility = VISIBLE
        textView3.visibility = VISIBLE
        textView4.visibility = VISIBLE
        textView5.visibility = VISIBLE
        nowplayingText.visibility = VISIBLE
        upcomingText.visibility = VISIBLE
        topText.visibility = VISIBLE
        trendingText.visibility = VISIBLE
        popularText.visibility = VISIBLE
        shimmerLayout.stopShimmer()
        shimmerLayout.hideView()
        constLayout.showView()
    }

    override fun onItemClick(movie: MovieUi) {
        openDialogSheet(movie)
    }

    override fun onLongItemClick(movie: MovieUi) {
        viewModel.saveMovie(movie = movie)
        makeToast(
            "Фильм (${movie.movieTitle}) Сохранён",
            context = requireContext()
        )
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}
