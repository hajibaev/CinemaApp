package com.example.mymovieapp.ui.actors.person_details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.data.cloud.utils.Utils.IMAGE_PATH
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.app.utils.motion.MotionListener
import com.example.mymovieapp.app.utils.motion.MotionState
import com.example.mymovieapp.databinding.FragmentPersonDetailsBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding, PersonDetailsViewModel>(
    FragmentPersonDetailsBinding::inflate
), RvClickListener<MovieUi> {

    private val movieAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val actorsIds by lazy { PersonDetailsFragmentArgs.fromBundle(requireArguments()).id }
    private val known_for by lazy { PersonDetailsFragmentArgs.fromBundle(requireArguments()).movie }

    private val args by navArgs<PersonDetailsFragmentArgs>()


    @Inject
    lateinit var viewModelFactory: PersonDetailsViewModelFactory.Factory
    override val viewModel by viewModels<PersonDetailsViewModel> {
        viewModelFactory.create(actorsIds = actorsIds)
    }
    private val motionListener = MotionListener(::setToolbarState)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupViews()
        if (known_for != null) {
            movieAdapter.moviesList = known_for!!.toList()
            requireBinding().personMoviesRv.adapter = movieAdapter
        }
    }

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }

    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().nestedScrollView3.smoothScrollTo(0, 0)
            }
            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            personFlow.observe(::observePersonUi)
        }
    }

    private fun observePersonUi(person: PersonDetailsPresentation) = with(requireBinding()) {
        includeUserInfoToolbarBlock.upButton.setOnDownEffectClick { viewModel.navigateBack() }
        includeUserInfoBlueToolbarBlock.backiccon.setOnDownEffectClick() { viewModel.navigateBack() }
        requireContext().showRoundedImage(
            imageUrl = IMAGE_PATH + person.profile_path,
            imageView = userImage
        )
        includeUserInfoToolbarBlock.userToolbarNameText.text = person.name
        birthday.text = person.birthday
        profession.text = person.known_for_department
        biography.text = person.biography
        birthday.text = person.birthday
        personGender.text = person.gender
        personName.text = person.name
        personPopularity.text = person.popularity.toFloat().toString()
        birthPlace.text = person.place_of_birth
    }

    override fun onItemClick(movie: MovieUi) = viewModel.launchMovieDetails(movie.movieId)


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
