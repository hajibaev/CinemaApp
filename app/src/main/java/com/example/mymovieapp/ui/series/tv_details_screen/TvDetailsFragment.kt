package com.example.mymovieapp.ui.series.tv_details_screen

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.movie.TvSeriesDetailsUi
import com.example.mymovieapp.app.utils.blur.BlurTransformation
import com.example.mymovieapp.app.utils.extensions.hideView
import com.example.mymovieapp.app.utils.extensions.launchWhenViewStarted
import com.example.mymovieapp.app.utils.extensions.makeToast
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClickListener
import com.example.mymovieapp.app.utils.motion.MotionListener
import com.example.mymovieapp.app.utils.motion.MotionState
import com.example.mymovieapp.databinding.FragmentTvDetailsBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TvDetailsFragment : BaseFragment<FragmentTvDetailsBinding, TvDetailsViewModel>(
    FragmentTvDetailsBinding::inflate
), RvClickListener<SeriesUi> {

    private val tvId: Int by lazy { TvDetailsFragmentArgs.fromBundle(requireArguments()).tv.id }
    private val similarAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val recommendAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    @Inject
    lateinit var viewModelFactory: TvDetailsViewModelFactory.Factory
    override val viewModel by viewModels<TvDetailsViewModel> {
        viewModelFactory.create(tvId = tvId)
    }
    private val motionListener = MotionListener(::setToolbarState)

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }

    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().includeBookInfoBlock.nestedScroolView.smoothScrollTo(0, 0)
            }
            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setAdaptersTv()
        setupClickers()
        setupViews()
    }

    private fun setAdaptersTv() = with(requireBinding()) {
        includeBookInfoBlock.apply {
            recommendMoviesRv.adapter = recommendAdapter
            similarMoviesRv.adapter = similarAdapter
        }
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe(::setMovieUi)
            similarMoviesFlow.observe { similarAdapter.moviesList = it.results }
            recommendMoviesFlow.observe { recommendAdapter.moviesList = it.results }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().isEmptyLoading.visibility = VISIBLE
        }
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
        includeBookInfoPosterBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
    }


    private fun setMovieUi(tv: TvSeriesDetailsUi) = with(requireBinding()) {
        includeBookInfoToolbarBlock.toolbarBookTitle.text = tv.originalName
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

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.changeMovieId(seriesUi.id)
    }

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: SeriesUi) {
        viewModel.saveTv(item)
        showSuccessSnackBar("Movie ${item.originalName} saved")
    }
}