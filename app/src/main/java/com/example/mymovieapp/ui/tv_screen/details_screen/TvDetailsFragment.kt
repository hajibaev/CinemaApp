package com.example.mymovieapp.ui.tv_screen.details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvDetailsBinding
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.ui.BlurTransformation
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.hideView
import com.example.mymovieapp.ui.launchWhenViewStarted
import com.example.mymovieapp.ui.makeToast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TvDetailsFragment : BaseFragment<FragmentTvDetailsBinding, TvDetailsViewModel>(
    FragmentTvDetailsBinding::inflate
), TvAdapter.RecyclerOnClickListener {

    private val tvId: Int by lazy { TvDetailsFragmentArgs.fromBundle(requireArguments()).tv.id }
    private val similarAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val recommendAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

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
        includeBookInfoBlock.apply {
            recommendMoviesRv.adapter = recommendAdapter
            similarMoviesRv.adapter = similarAdapter
        }
    }

    private fun observe() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe(::setMovieUi)
            similarMoviesFlow.observe { similarAdapter.moviesList = it.results }
            recommendMoviesFlow.observe { recommendAdapter.moviesList = it.results }
        }
//
//
//        lifecycleScope.launchWhenStarted {
//            movieFlow.collectLatest {
//                setMovieUi(it)
//            }
//        }
//        lifecycleScope.launchWhenStarted {
//            similarMoviesFlow.collectLatest {
//                similarAdapter.moviesList = it.results
//            }
//        }
//        lifecycleScope.launchWhenResumed {
//            recommendMoviesFlow.collectLatest {
//                recommendAdapter.moviesList = it.results
//            }
//        }
        error.onEach {
            makeToast(it, requireContext())
        }

    }


    private fun setMovieUi(tv: TvSeriesDetailsUi) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = tv.originalName
        includeBookInfoToolbarBlock.backIcon.setOnClickListener { viewModel.goBack() }
        includeBookInfoPosterBlock.apply {
            Picasso.get().load(Utils.IMAGE_PATH + tv.posterPath).into(moviePoster)
            Glide.with(requireContext()).asBitmap().load(Utils.IMAGE_PATH + tv.posterPath)
                .transform(BlurTransformation(requireContext())).into(bookBlurBackgroundPoster)
            moviemovieTitle.text = tv.originalName
        }
        includeBookInfoBlock.apply {
            bookPublicYear.text = tv.voteCount.toString()
            bookChapterCount.text = tv.numberOfSeasons.toString()
            bookPageCount.text = tv.popularity.toString()
            movieBudgetText.text = tv.numberOfEpisodes.toString()
            movieVotedText.text = tv.originCountry.toString()
            voteAverage.rating = tv.voteAverage.toFloat()
            originalLanguage.text = tv.firstAirDate
            status.text = tv.status
            slide.text = tv.tagline
            overview.text = tv.overview
        }
    }

    override fun onItemClick(item: SeriesUi) {
        viewModel.changeMovieId(item.id)
    }

    override fun onLongItemClick(item: SeriesUi) {
        viewModel.saveTv(item)
        makeToast("Фильм (${item.originalName}) Сохранён", requireContext())
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}