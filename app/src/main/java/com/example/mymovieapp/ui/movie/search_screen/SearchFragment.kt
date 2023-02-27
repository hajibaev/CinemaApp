package com.example.mymovieapp.ui.movie.search_screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.broadcast.myapplication.adapter.animations.AddableItemAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInLeftCommonAnimator
import com.broadcast.myapplication.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseFragment
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.utils.extensions.*
import com.example.mymovieapp.databinding.FragmentSearchBinding
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.movie.MovieAdapter
import com.example.mymovieapp.ui.see_more_sort_dialog.SettingsFragment
import com.example.mymovieapp.ui.type.SeeAllMovieType
import com.example.mymovieapp.ui.type.SeeAllTvType
import com.example.ui_core.modal_page.ModalPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate),
    RvClickListener<MovieUi> {
    override val viewModel: SearchViewModel by viewModels()


    private val moviesAdapter by lazy { MovieAdapter(MovieAdapter.PORTRAIT_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        adapterAnim()
        setUi()
        setupClickers()
        applyPosterImages()
    }


    private fun observe() = launchWhenViewStarted {
        viewModel.searchMovie().observe { moviesAdapter.moviesList = it.movies }
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
    }

    private fun applyPosterImages() = with(requireBinding()) {
        requireContext().apply {
            showRoundedImage(
                imageUrl = getString(R.string.action_image),
                imageView = genresAction.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.adventure_image),
                imageView = genresAdventure.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.animations_image2),
                imageView = genresAnimation.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.comedy_image2),
                imageView = genresComedy.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.crime_image),
                imageView = genresCrime.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.drama_image),
                imageView = genresDrama.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.family_image2),
                imageView = genresFamily.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.fantasy_image),
                imageView = genresFantasy.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.history_image2),
                imageView = genresHistory.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.horror_image2),
                imageView = genresHorror.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.music_image),
                imageView = genresMusic.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.mystery_image),
                imageView = genresMystery.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.romance_image),
                imageView = genresRomance.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.scienceFiction_image),
                imageView = genresSciencefiction.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.tvMovie_image3),
                imageView = genresTvMovie.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.documentary_image),
                imageView = genresDocumentary.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.thriller_image),
                imageView = genresThriller.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.war_image),
                imageView = genresWar.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.western_image),
                imageView = genresWestern.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.kids_image),
                imageView = genresKids.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.news_image),
                imageView = genresNews.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.reality_image),
                imageView = genresReality.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.fantasy_image_tv),
                imageView = genresFantasyTv.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.soap_image_tv),
                imageView = genresSoapTv.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.talk_image_tv),
                imageView = genresTalkTv.cover
            )
            showRoundedImage(
                imageUrl = getString(R.string.politics_image_tv),
                imageView = genresPoliticsTv.cover
            )
        }
    }

    private fun setUi() = with(requireBinding()) {
        searchRv.adapter = moviesAdapter
        genresAction.title.text = getString(R.string.genres_action)
        genresAdventure.title.text = getString(R.string.genres_adventure)
        genresAnimation.title.text = getString(R.string.genres_animation)
        genresComedy.title.text = getString(R.string.genres_comedy)
        genresCrime.title.text = getString(R.string.genres_crime)
        genresDocumentary.title.text = getString(R.string.genres_documentary)
        genresDrama.title.text = getString(R.string.genres_drama)
        genresFamily.title.text = getString(R.string.genres_family)
        genresFantasy.title.text = getString(R.string.genres_fantasy)
        genresHistory.title.text = getString(R.string.genres_history)
        genresHorror.title.text = getString(R.string.genres_horror)
        genresMusic.title.text = getString(R.string.genres_music)
        genresMystery.title.text = getString(R.string.genres_mystery)
        genresRomance.title.text = getString(R.string.genres_romance)
        genresSciencefiction.title.text = getString(R.string.genres_scienceFiction)
        genresTvMovie.title.text = getString(R.string.genres_tvMovie)
        genresThriller.title.text = getString(R.string.genres_thriller)
        genresWar.title.text = getString(R.string.genres_war)
        genresWestern.title.text = getString(R.string.genres_western)
        genresKids.title.text = getString(R.string.kids_genres)
        genresNews.title.text = getString(R.string.news_genres)
        genresReality.title.text = getString(R.string.reality_genres)
        genresFantasyTv.title.text = getString(R.string.genres_fantasy)
        genresSoapTv.title.text = getString(R.string.soap_genres)
        genresTalkTv.title.text = getString(R.string.talk_genres)
        genresPoliticsTv.title.text = getString(R.string.politics_genres)
    }

    private fun setupClickers() = with(requireBinding()) {
        with(viewModel) {
            genresAction.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.ACTION, SeeAllTvType.ACTION)
            }
            genresAnimation.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.ANIMATION, SeeAllTvType.ANIMETYPE)
            }
            genresComedy.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.COMEDY, SeeAllTvType.COMEDY)
            }
            genresCrime.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.CRIME, SeeAllTvType.CRIME)
            }
            genresDocumentary.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.DOCUMENTARY, SeeAllTvType.DOCUMENTARY)
            }
            genresDrama.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.DRAMA, SeeAllTvType.DRAMATYPE)
            }
            genresFamily.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.FAMILY, SeeAllTvType.FAMILYTYPE)
            }
            genresHistory.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.HISTORY, SeeAllTvType.HISTORY)
            }
            genresMystery.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.MYSTERY, SeeAllTvType.MYSTERY)
            }
            genresWestern.container.setOnDownEffectClick {
                showSettingModalPage(SeeAllMovieType.WESTERN, SeeAllTvType.WESTERN)
            }
            genresAdventure.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.ADVENTURE) }
            genresFantasy.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.FANTASY) }
            genresHorror.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.HORROR) }
            genresMusic.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.MUSIC) }
            genresRomance.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.ROMANCE) }
            genresSciencefiction.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.SCIENCEFICTION) }
            genresTvMovie.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.TV_MOVIE) }
            genresThriller.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.THRILLER) }
            genresWar.container.setOnDownEffectClick { launchMovieType(SeeAllMovieType.WAR) }
            genresKids.container.setOnDownEffectClick { launchTvType(SeeAllTvType.KIDS) }
            genresNews.container.setOnDownEffectClick { launchTvType(SeeAllTvType.NEWS) }
            genresReality.container.setOnDownEffectClick { launchTvType(SeeAllTvType.REALITY) }
            genresFantasyTv.container.setOnDownEffectClick { launchTvType(SeeAllTvType.FANTASY) }
            genresSoapTv.container.setOnDownEffectClick { launchTvType(SeeAllTvType.SOAP) }
            genresTalkTv.container.setOnDownEffectClick { launchTvType(SeeAllTvType.TALK) }
            genresPoliticsTv.container.setOnDownEffectClick { launchTvType(SeeAllTvType.POLITICS) }
        }
    }

    private fun setVisible() = with(requireBinding()) {
        searchRv.showView()
        itemMovie.hideView()
    }

    private fun setInvisible() = with(requireBinding()) {
        searchRv.hideView()
        itemMovie.showView()
    }

    private fun search() = requireBinding().searchView.apply {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != "") {
                    setVisible()
                    observe()
                    viewModel.newTryEmitQuery(query!!)
                } else {
                    setInvisible()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != "") {
                    setVisible()
                    observe()
                    viewModel.newTryEmitQuery(newText!!)
                } else {
                    setInvisible()
                }
                return true
            }
        })
        setOnCloseListener {
            false
        }
    }

    private fun showSettingModalPage(seeAllMovie: SeeAllMovieType, seeAllTv: SeeAllTvType) =
        SettingsFragment.newInstance(
            getString(R.string.setting),
            seeAllMovieType = seeAllMovie,
            seeAllTvType = seeAllTv
        )
            .show(requireActivity().supportFragmentManager, ModalPage.TAG)

    private fun adapterAnim() = with(requireBinding()) {
        searchRv.itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_portrait_item,
                SlideInTopCommonAnimator()
            )
            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }


    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie.movieId)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onLongClick(item: MovieUi) {
        viewModel.saveMovie(item)
        showSuccessSnackBar("Movie ${item.movieTitle} saved")
    }
}