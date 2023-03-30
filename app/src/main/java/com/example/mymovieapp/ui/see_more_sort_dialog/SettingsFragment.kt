package com.example.mymovieapp.ui.see_more_sort_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClick
import com.example.mymovieapp.databinding.FragmentSettingsBinding
import com.example.mymovieapp.ui.movie.search_screen.SearchFragmentDirections
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.example.mymovieapp.ui.type.SeeAllTvType
import com.example.ui_core.custom.snackbar.SnackBar
import com.example.ui_core.modal_page.ModalPage
import com.example.ui_core.modal_page.dismissModalPage

class SettingsFragment : DialogFragment() {

    private var _binding: FragmentSettingsBinding? = null
    val binding get() = _binding ?: bindingLifecycleError()

    private var seeAllMovie: SeeAllMovieType? = null
    private var seeAllTv: SeeAllTvType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvShows.setOnDownEffectClick {
            findNavController().navigate(
                SearchFragmentDirections.actionNavSearchToTvTypeFragment(
                    seeAllTv!!
                )
            )
            dismissModalPage()
        }


        binding.movie.setOnDownEffectClick {
            findNavController().navigate(
                SearchFragmentDirections.actionNavSearchToMovieTypeFragment(
                    seeAllMovie!!
                )
            )
            dismissModalPage()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(
            title: String,
            seeAllMovieType: SeeAllMovieType,
            seeAllTvType: SeeAllTvType
        ) = SettingsFragment().run {
            this.seeAllMovie = seeAllMovieType
            this.seeAllTv = seeAllTvType
            ModalPage.Builder()
                .fragment(this)
                .title(title)
                .build()
        }
    }
}

/** Throws an [IllegalStateException] with binding lifecycle error message. */
inline fun bindingLifecycleError(): Nothing = throw IllegalStateException(
    "This property is only valid between onCreateView and onDestroyView."
)