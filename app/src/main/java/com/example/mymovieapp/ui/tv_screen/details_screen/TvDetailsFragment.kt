package com.example.mymovieapp.ui.tv_screen.details_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvDetailsBinding
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.ui.BlurTransformation
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.makeToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tv_details.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TvDetailsFragment : BaseFragment<FragmentTvDetailsBinding, TvDetailsViewModel>(
    FragmentTvDetailsBinding::inflate
), TvAdapter.RecyclerOnClickListener {
    private val tvId: Int by lazy {
        TvDetailsFragmentArgs.fromBundle(requireArguments()).tv.id
    }
    private val similarAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val recommendAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }

    @Inject
    lateinit var viewModelFactory: TvDetailsViewModelFactory.Factory
    override val viewModel by viewModels<TvDetailsViewModel> {
        viewModelFactory.create(tvId = tvId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setAdaptersTv()
    }

    private fun setAdaptersTv() = with(requireBinding()) {
        recommendMoviesRv.adapter = recommendAdapter
        similarMoviesRv.adapter = similarAdapter
    }

    private fun observe() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            movieFlow.collectLatest {
                setMovieUi(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            similarMoviesFlow.collectLatest {
                similarAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            recommendMoviesFlow.collectLatest {
                recommendAdapter.moviesList = it.results
            }
        }
        error.onEach {
            makeToast(it, requireContext())
        }

    }


    private fun setMovieUi(tv: TvSeriesDetailsUi) = with(requireBinding()) {
        topTitle.text = tv.originalName
        movie_name.text = tv.originalName
        voteCount.text = tv.voteCount.toString()
        movie_time.text = tv.numberOfSeasons.toString()
        movie_popularity.text = tv.popularity.toString()
        movie_budget_text.text = tv.numberOfEpisodes.toString()
        movie_voted_text.text = tv.originCountry.toString()
        voteAverage.rating = tv.voteAverage.toFloat()
        originalLanguage.text = tv.firstAirDate
        status.text = tv.status
        slide.text = tv.tagline
        overview.text = tv.overview
        Glide.with(requireContext()).asBitmap()
            .load(Utils.IMAGE_PATH + tv.backdropPath)
            .transform(BlurTransformation(requireContext())).into(topMainImage)
        Picasso.get().load(Utils.IMAGE_PATH + tv.posterPath).into(posterImage)

    }

    override fun onItemClick(item: SeriesUi) {
        viewModel.changeMovieId(item.id)
        requireBinding().nestedScrollView2.fullScroll(ScrollView.FOCUS_UP)
    }

    override fun onLongItemClick(item: SeriesUi) {
        viewModel.saveTv(item)
        makeToast(
            "Фильм (${item.originalName}) Сохранён",
            context = requireContext()
        )
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}