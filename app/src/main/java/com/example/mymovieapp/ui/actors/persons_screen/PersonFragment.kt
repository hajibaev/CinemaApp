package com.example.mymovieapp.ui.actors.persons_screen

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentPersonBinding
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.models.person.PersonPresentation
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.person.PersonItemAdapter
import com.example.mymovieapp.app.utils.extensions.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        adapterAnimation()
    }

    private fun observe() = with(viewModel) {
        error.onEach {
            makeToast(it, requireContext())
        }
        launchWhenViewStarted {
            persons.observe {
                personsAdapter.personsList = it.persons
                uiVisible()
            }
            personResponseState.observe(::observeResponseState)
        }
    }

    private fun uiVisible() = with(requireBinding()) {
        lifecycleScope.launch {
            delay(600L)
            pageConstraint.showView()
        }
        isEmptyLoading.hideView()
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

    private fun adapterAnimation() = with(requireBinding()) {
        personsRv.itemAnimator = AddableItemAnimator(SlideInTopCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.storage_item,
                SlideInLeftCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    override fun onItemClick(item: PersonPresentation) {
        viewModel.launchPersonDetails(item)
    }

    override fun onLongClick(item: PersonPresentation) = makeToast(item.name, requireContext())

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}