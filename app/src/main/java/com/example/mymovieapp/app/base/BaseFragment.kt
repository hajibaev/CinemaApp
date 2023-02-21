package com.example.mymovieapp.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.mymovieapp.app.utils.extensions.launchWhenViewStarted
import com.example.mymovieapp.app.utils.extensions.navigateTo
import com.example.mymovieapp.app.utils.extensions.observeNonNull
import com.example.mymovieapp.app.utils.navigation.NavigationCommand
import com.example.ui_core.custom.snackbar.SnackBar

abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> V,
) : Fragment() {

    protected abstract val viewModel: VM

    private var binding: V? = null

    protected fun requireBinding(): V = checkNotNull(binding)

    protected abstract fun onReady(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = binder.invoke(inflater, container, false)
        this.binding = binding
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
        onReady(savedInstanceState)
        observeRecourse()

    }

    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            navCommand.observe(findNavController()::navigateTo)
        }
        collectNavigation(viewLifecycleOwner) {
            it.getValue()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getValue()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }


    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun showSuccessSnackBar(message: String) =
        SnackBar
            .Builder(binding!!.root)
            .success()
            .message(message)
            .build()
            .show()

    fun showErrorSnackbar(message: String) =
        SnackBar
            .Builder(binding!!.root)
            .error()
            .message(message)
            .build()
            .show()

    fun showWarningSnackbar(message: String) =
        SnackBar
            .Builder(binding!!.root)
            .warning()
            .message(message)
            .build()
            .show()


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val COLLAPSED = 1f
        const val EXPANDED = 0f
        const val DEFAULT_ITEMS_ANIMATOR_DURATION = 500L
    }

}