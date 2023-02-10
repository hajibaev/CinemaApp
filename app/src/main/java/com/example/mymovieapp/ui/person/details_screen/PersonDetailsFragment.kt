package com.example.mymovieapp.ui.person.details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.cloud.server.Utils.IMAGE_PATH
import com.example.domain.DataRequestState
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentPersonDetailsBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.person.PersonDetailsPresentation
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.makeToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding, PersonDetailsViewModel>(
    FragmentPersonDetailsBinding::inflate
), MovieAdapter.RecyclerOnClickListener {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(MovieAdapter.HORIZONTAL_TYPE, this)
    }
    private val actorsIds: Int by lazy {
        PersonDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).person.id
    }

    private val known_for by lazy {
        PersonDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).person.known_for
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

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.personFlow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        observeUi(it.data)
                    }
                    is DataRequestState.Error -> {
                        makeToast(it.error.message.toString(), requireContext())
                    }
                }
            }
        }
        movieAdapter.moviesList = known_for
        requireBinding().moviesRecyclerView.adapter = movieAdapter
    }


    private fun observeUi(person: PersonDetailsPresentation) {
        Picasso.get().load(IMAGE_PATH + person.profile_path).into(requireBinding().personImage)
        requireBinding().apply {
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

    }

    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onLongItemClick(movie: MovieUi) {
        viewModel.saveMovie(movie)
        makeToast(
            "Фильм (${movie.movieTitle}) Сохранён",
            context = requireContext()
        )
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}
