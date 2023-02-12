package com.example.mymovieapp.ui.person.persons_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentPersonBinding
import com.example.mymovieapp.models.movie.ResponseState
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.person.PersonItemAdapter
import com.example.mymovieapp.utils.extensions.launchWhenViewStarted
import com.example.mymovieapp.utils.extensions.makeToast
import com.example.mymovieapp.utils.extensions.setOnDownEffectClickListener
import com.example.mymovieapp.utils.extensions.showView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PersonFragment : BaseFragment<FragmentPersonBinding, PersonViewModel>(
    FragmentPersonBinding::inflate
), RvClickListener<PersonPresentation> {
    override val viewModel by viewModels<PersonViewModel>()

    private val personsAdapter by lazy { PersonItemAdapter(this@PersonFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiAndClickers()
        requireBinding().personsRv.adapter = personsAdapter
        observe()
    }

    private fun observe() = with(viewModel) {
        error.onEach {
            makeToast(it, requireContext())
        }
        launchWhenViewStarted {
            persons.observe { personsAdapter.personsList = it.persons }
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
            personResponseState.observe(::observeResponseState)
        }
    }


    private fun observeResponseState(state: ResponseState) = with(requireBinding()) {
        prevPageText.text = state.previousPage.toString()
        currentPageText.text = state.page.toString()
        nextPageText.text = state.nextPage.toString()
        prevBtn.apply {
            isClickable = state.isHasPreviousPage
            isFocusable = state.isHasPreviousPage
        }
        nextBtn.apply {
            isClickable = state.isHasNextPage
            isFocusable = state.isHasNextPage
        }
    }

    private fun setupUiAndClickers() {
        requireBinding().apply {
            nextBtn.setOnDownEffectClickListener() {
                viewModel.nextPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnDownEffectClickListener {
                viewModel.previousPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    override fun onItemClick(item: PersonPresentation) = viewModel.launchPersonDetails(item)

    override fun onLongClick(item: PersonPresentation) = makeToast(item.name, requireContext())

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}