package com.example.mymovieapp.ui.movie.movie_details_screen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.CastUi
import com.example.mymovieapp.app.models.movie.MovieDetailsUi
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.blur.BlurTransformation
import com.example.mymovieapp.app.utils.extensions.hideView
import com.example.mymovieapp.app.utils.extensions.launchWhenViewStarted
import com.example.mymovieapp.app.utils.extensions.makeToast
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClickListener
import com.example.mymovieapp.app.utils.motion.MotionListener
import com.example.mymovieapp.app.utils.motion.MotionState
import com.example.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter.Companion.HORIZONTAL_TYPE
import com.example.mymovieapp.ui.adapters.person.ActorsAdapters
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(FragmentMovieDetailsBinding::inflate),
    RvClickListener<MovieUi>,
    ActorsAdapters.RvClickListener {

    private val movieId by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.movieId }

    @Inject
    lateinit var viewModelFactory: MovieDetailsViewModelFactory.Factory
    override val viewModel by viewModels<MovieDetailsViewModel> { viewModelFactory.create(movieId = movieId) }

    private val similarMoviesAdapter by lazy {
        MovieAdapter(HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }
    private val recommendMoviesAdapter by lazy {
        MovieAdapter(HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }
    private val personAdapter by lazy { ActorsAdapters(this@MovieDetailsFragment) }
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
        setupAdapters()
        observe()
        setupViews()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoToolbarBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
        includeBookInfoPosterBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
    }

    private fun setupAdapters() = with(requireBinding()) {
        includeBookInfoBlock.apply {
            recommendMoviesRv.adapter = recommendMoviesAdapter
            similarMoviesRv.adapter = similarMoviesAdapter
            actorsRv.adapter = personAdapter
        }
    }

    private fun observe() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe(::setMovieUi)
            actorsFlow.observe { personAdapter.personsList = it.cast }
            similarMoviesFlow.observe { similarMoviesAdapter.moviesList = it.movies }
            recommendMoviesFlow.observe { recommendMoviesAdapter.moviesList = it.movies }
        }
        error.onEach {
            makeToast(it, requireContext())
        }
    }

    private fun setMovieUi(movie: MovieDetailsUi) {
        with(requireBinding()) {
            includeBookInfoToolbarBlock.toolbarBookTitle.text = movie.originalTitle
            with(includeBookInfoPosterBlock) {
                Picasso.get().load(Utils.IMAGE_PATH + movie.posterPath)
                    .into(moviePoster)
                Glide.with(requireContext()).asBitmap()
                    .load(Utils.IMAGE_PATH + movie.posterPath)
                    .transform(BlurTransformation(requireContext()))
                    .into(bookBlurBackgroundPoster)
                moviemovieTitle.text = movie.originalTitle
            }
            with(includeBookInfoBlock) {
                bookPublicYear.text = movie.releaseDate
                bookChapterCount.text = movie.runtime.toString()
                bookPageCount.text = movie.voteAverage.toString()
                movieBudgetText.text = movie.budget.toString()
                movieVotedText.text = movie.voteCount.toString()
                voteAverage.rating = movie.voteAverage.toFloat()
                originalLanguage.text = movie.originalLanguage
                status.text = movie.status
                overview.text = movie.overview
            }
        }
    }


    override fun onItemClick(item: MovieUi) {
        Animatoo.animateDiagonal(requireActivity())
        viewModel.changeMovieId(item.movieId)
    }

    override fun onPersonItemClick(person: CastUi) =
        showWarningSnackbar("Not yet ready to show")

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}
