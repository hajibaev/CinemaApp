package com.example.mymovieapp.ui.series.tv_details_screen

import android.animation.ObjectAnimator
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
import com.example.mymovieapp.app.models.person.CastUi
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.utils.blur.BlurTransformation
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.app.utils.motion.MotionListener
import com.example.mymovieapp.app.utils.motion.MotionState
import com.example.mymovieapp.databinding.FragmentTvDetailsBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.adapters.person.ActorsAdapters
import com.example.mymovieapp.ui.adapters.person.CrewAdapter
import com.example.mymovieapp.ui.movie.movie_details_screen.MovieDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TvDetailsFragment : BaseFragment<FragmentTvDetailsBinding, TvDetailsViewModel>(
    FragmentTvDetailsBinding::inflate
), RvClickListener<SeriesUi>, ActorsAdapters.RvClickListener, CrewAdapter.RecyclerOnClickListener {

    private val tvId: Int by lazy { TvDetailsFragmentArgs.fromBundle(requireArguments()).tvId }
    private val similarAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val recommendAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val crewAdapter by lazy { CrewAdapter(this) }


    @Inject
    lateinit var viewModelFactory: TvDetailsViewModelFactory.Factory
    override val viewModel by viewModels<TvDetailsViewModel> {
        viewModelFactory.create(tvId = tvId)
    }
    private val motionListener = MotionListener(::setToolbarState)

    private val personAdapter by lazy { ActorsAdapters(this) }

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
            actorsRv.adapter = personAdapter
            crewRv.adapter = crewAdapter
        }
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe(::setMovieUi)
            similarMoviesFlow.observe { similarAdapter.moviesList = it.results }
            actorsFlow.observe { personAdapter.personsList = it.cast }
            actorsFlow.observe { crewAdapter.crewList = it.crew }
            recommendMoviesFlow.observe { recommendAdapter.moviesList = it.results }
        }
        error.onEach {
            showErrorSnackbar(it)
            requireBinding().isEmptyLoading.visibility = VISIBLE
        }
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClick { viewModel.navigateBack() }
        includeBookInfoPosterBlock.backIcon.setOnDownEffectClick { viewModel.navigateBack() }
    }


    private fun setMovieUi(tv: TvSeriesDetailsUi) = with(requireBinding()) {
        val screenWidth = 0f
        val animationSpeed = 1f
        with(includeBookInfoToolbarBlock) {
            toolbarBookTitle.text = tv.originalName
            toolbarBookTitle.isSelected = true
            toolbarBookTitle.translationX = -screenWidth
            val duration = (200 * animationSpeed).toLong()
            ObjectAnimator.ofFloat(toolbarBookTitle, "translationX", 0f).setDuration(duration)
                .start()
        }
        includeBookInfoPosterBlock.apply {
            applyPosterImages(tv.posterPath, tv.backdropPath)
            moviemovieTitle.text = tv.originalName
            moviemovieTitle.isSelected = true
            moviemovieTitle.translationX = -screenWidth
            val duration = (200 * animationSpeed).toLong()
            ObjectAnimator.ofFloat(moviemovieTitle, "translationX", 0f).setDuration(duration)
                .start()
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

    private fun applyPosterImages(posterUrl: String?, backdrop_path: String?) {
        requireContext().showRoundedImage(
            imageUrl = Utils.IMAGE_PATH + posterUrl,
            imageView = requireBinding().includeBookInfoPosterBlock.moviePoster
        )
        requireContext().showBlurImage(
            blurSize = BACKGROUND_IMAGE_BLUR_SIZE,
            imageUrl = Utils.IMAGE_PATH + backdrop_path,
            imageView = requireBinding().includeBookInfoPosterBlock.bookBlurBackgroundPoster
        )
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

    override fun onPersonItemClick(person: CastUi) {
        viewModel.goActorsDetails(person)
    }

    private companion object {
        const val BACKGROUND_IMAGE_BLUR_SIZE = 25f
    }

    override fun onItemClick(crewUi: CrewUi) = viewModel.goFromCrewToActorsDetails(crewUi)


}