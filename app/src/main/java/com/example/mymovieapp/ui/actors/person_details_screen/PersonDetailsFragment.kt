package com.example.mymovieapp.ui.actors.person_details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.data.cloud.utils.Utils.IMAGE_PATH
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentPersonDetailsBinding
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.app.utils.extensions.hideView
import com.example.mymovieapp.app.utils.extensions.launchWhenViewStarted
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding, PersonDetailsViewModel>(
    FragmentPersonDetailsBinding::inflate
), RvClickListener<MovieUi> {

    private val movieAdapter by lazy { MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this) }
    private val actorsIds by lazy { PersonDetailsFragmentArgs.fromBundle(requireArguments()).person.id }

    private val known_for by lazy {
        PersonDetailsFragmentArgs.fromBundle(requireArguments()).person.known_for
    }

    @Inject
    lateinit var viewModelFactory: PersonDetailsViewModelFactory.Factory
    override val viewModel by viewModels<PersonDetailsViewModel> {
        viewModelFactory.create(actorsIds = actorsIds)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            personFlow.observe(::observeUi)
            movieAdapter.moviesList = known_for
            requireBinding().moviesRecyclerView.adapter = movieAdapter
        }
    }

    private fun observeUi(person: PersonDetailsPresentation) = with(requireBinding()) {
        Picasso.get().load(IMAGE_PATH + person.profile_path).into(requireBinding().personImage)
        namePerson.text = person.name
        gender.text = person.gender
        personPopularityView.rating = person.popularity.toFloat()
        date.text = person.birthday
        profession.text = person.known_for_department
        biography.text = person.biography
        birthday.text = person.birthday
        deathDay.text = person.deathDay
        personGender.text = person.gender
        personName.text = person.name
        personPopularity.rating = person.popularity.toFloat()
        birthPlace.text = person.place_of_birth
    }

    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).hideView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}