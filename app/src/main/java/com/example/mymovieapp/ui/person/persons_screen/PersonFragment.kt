package com.example.mymovieapp.ui.person.persons_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentPersonBinding
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.person.PersonItemAdapter
import com.example.mymovieapp.ui.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PersonFragment : BaseFragment<FragmentPersonBinding, PersonViewModel>(
    FragmentPersonBinding::inflate
), RvClickListener<PersonPresentation> {
    override val viewModel by viewModels<PersonViewModel>()

    private val personsAdapter: PersonItemAdapter by lazy {
        PersonItemAdapter(this@PersonFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiAndClickers()
        observe()
    }

    private fun observe() = with(viewModel) {
        error.onEach {
            makeToast(it, requireContext())
        }
        lifecycleScope.launchWhenResumed {
            persons.collectLatest {
                personsAdapter.personsList = it.persons
                requireBinding().pageConstraint.visibility = View.VISIBLE
                requireBinding().isEmptyLoading.visibility = View.GONE
            }
        }
        lifecycleScope.launchWhenResumed {
            personResponseState.collectLatest { state ->
                requireBinding().apply {
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
            }
        }
    }

    private fun setupUiAndClickers() {
        requireBinding().apply {
            personsRv.adapter = personsAdapter
            nextBtn.setOnClickListener {
                viewModel.nextPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnClickListener {
                viewModel.previousPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    override fun onItemClick(item: PersonPresentation) {
        viewModel.launchPersonDetails(item)
    }

    override fun onLongClick(item: PersonPresentation) {
        makeToast(item.name, requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}