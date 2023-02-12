package com.example.mymovieapp.ui.movie.details_screen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.example.mymovieapp.models.movie.CastUi
import com.example.mymovieapp.models.movie.MovieDetailsUi
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.adapters.person.ActorsAdapters
import com.example.mymovieapp.utils.extensions.BlurTransformation
import com.example.mymovieapp.utils.extensions.hideView
import com.example.mymovieapp.utils.extensions.makeToast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(FragmentMovieDetailsBinding::inflate),
    MovieAdapter.RecyclerOnClickListener,
    ActorsAdapters.RvClickListener {

    private val movieId: Int by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.movieId
    }

    @Inject
    lateinit var viewModelFactory: MovieDetailsViewModelFactory.Factory
    override val viewModel by viewModels<MovieDetailsViewModel> {
        viewModelFactory.create(movieId = movieId)
    }

    private val similarMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }

    private val recommendMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }

    private val personAdapter: ActorsAdapters by lazy {
        ActorsAdapters(this@MovieDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        observe()
        setupClickers()
    }

    private fun setupClickers() = with(requireBinding()) {
        includeBookInfoBlock.apply {
            castSizeText.setOnClickListener {
                personAdapter.fixedSize = false
                actorsRv.adapter = personAdapter
                castSizeText.visibility = View.GONE
            }
        }
        includeBookInfoToolbarBlock.backIcon.setOnClickListener { viewModel.goBack() }
    }

    private fun setupAdapters() = with(requireBinding()) {
        includeBookInfoBlock.apply {
            recommendMoviesRv.adapter = recommendMoviesAdapter
            similarMoviesRv.adapter = similarMoviesAdapter
            actorsRv.adapter = personAdapter
        }
    }

    private fun observe() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            movieFlow.collectLatest {
                setMovieUi(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            actorsFlow.collectLatest {
                personAdapter.personsList = it.cast
            }
        }
        lifecycleScope.launchWhenResumed {
            similarMoviesFlow.collectLatest {
                similarMoviesAdapter.moviesList = it.movies
            }
        }
        lifecycleScope.launchWhenResumed {
            recommendMoviesFlow.collectLatest {
                recommendMoviesAdapter.moviesList = it.movies
            }
        }
        error.onEach {
            makeToast(it, requireContext())
        }
    }

    private fun openCastDialogSheet(item: CastUi) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.item_movie_2)
        val castImage = bottomSheet.findViewById<ImageView>(R.id.profile_picture)
        val name = bottomSheet.findViewById<TextView>(R.id.name)
        val pop = bottomSheet.findViewById<TextView>(R.id.popularitycast)
        val know_for_department = bottomSheet.findViewById<TextView>(R.id.know_for_department)
        val gender = bottomSheet.findViewById<TextView>(R.id.gender)
        val character = bottomSheet.findViewById<TextView>(R.id.character)
        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_btn)
        cancelBtn?.setOnClickListener {
            bottomSheet.dismiss()
        }
        know_for_department?.text = item.knownForDepartment
        if (item.gender == 1) gender?.text = "Female"
        else gender?.text = "Male"
        character?.text = item.character
        pop?.text = item.popularity.toString()
        Picasso.get().load(Utils.IMAGE_PATH + item.profilePath).into(castImage)
        name?.text = item.name
        bottomSheet.setCancelable(true)
        bottomSheet.show()
    }

    private fun setMovieUi(movie: MovieDetailsUi) {
        with(requireBinding()) {
            includeBookInfoToolbarBlock.toolbarBookTitle.text = movie.originalTitle
            Picasso.get().load(Utils.IMAGE_PATH + movie.posterPath)
                .into(includeBookInfoPosterBlock.moviePoster)
            Glide.with(requireContext()).asBitmap()
                .load(Utils.IMAGE_PATH + movie.posterPath)
                .transform(BlurTransformation(requireContext()))
                .into(includeBookInfoPosterBlock.bookBlurBackgroundPoster)
            includeBookInfoPosterBlock.moviemovieTitle.text = movie.originalTitle
            includeBookInfoBlock.apply {
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
        viewModel.changeMovieId(item.movieId)
    }

    override fun onLongItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("Фильм (${item.movieTitle}) Сохранён", context = requireContext())
    }

    override fun onPersonItemClick(person: CastUi) {
        openCastDialogSheet(person)
    }
    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}
