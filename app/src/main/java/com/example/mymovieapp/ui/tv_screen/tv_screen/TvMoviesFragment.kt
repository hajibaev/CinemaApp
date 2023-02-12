package com.example.mymovieapp.ui.tv_screen.tv_screen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.base.BaseFragment
import com.example.mymovieapp.databinding.FragmentTvMoviesBinding
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.*
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.see_all_screen.SeeAllTvType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TvMoviesFragment : BaseFragment<FragmentTvMoviesBinding, TvMoviesFragmentViewModel>(
    FragmentTvMoviesBinding::inflate
), TvAdapter.RecyclerOnClickListener {

    override val viewModel: TvMoviesFragmentViewModel by viewModels()

    private val trendingRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val airingTodayRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val familyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val dramaMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val animeMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val comedyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val historyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val mysteryMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val westernMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterToRv()
        observeViewModel()
        setupClickers()
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        tvTrendingRv.adapter = trendingRv
        tvTopretedRv.adapter = topRatedRv
        tvOnTheAirRv.adapter = onTheAirRv
        tvPopularRv.adapter = popularRv
        tvAiringTodayRv.adapter = airingTodayRv
        familyRV.adapter = familyMoviesAdapter
        animatoinRv.adapter = animeMoviesAdapter
        dramaRv.adapter = dramaMoviesAdapter
        comedyRv.adapter = comedyMoviesAdapter
        mysteryRv.adapter = mysteryMoviesAdapter
        westernRv.adapter = westernMoviesAdapter
        historyRv.adapter = historyMoviesAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            tvTrending.observe { trendingRv.moviesList = it.results }
            tvTopRated.observe { topRatedRv.moviesList = it.results }
            tvOnTheAir.observe { onTheAirRv.moviesList = it.results }
            tvPopular.observe { popularRv.moviesList = it.results }
            tvAiringToday.observe { airingTodayRv.moviesList = it.results }
            familyMovies.observe { familyMoviesAdapter.moviesList = it.results }
            anime.observe { animeMoviesAdapter.moviesList = it.results }
            dramaMovies.observe { dramaMoviesAdapter.moviesList = it.results }
            comedyMovies.observe { comedyMoviesAdapter.moviesList = it.results }
            historyMovies.observe { historyMoviesAdapter.moviesList = it.results }
            westernMovies.observe { westernMoviesAdapter.moviesList = it.results }
            mysteryMovies.observe { mysteryMoviesAdapter.moviesList = it.results }
            uiVisibility()
        }
        error.onEach {
            makeToast(it, requireContext())
            uiVisibility()
        }
    }


    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = View.INVISIBLE
        shimmerLayout.hideView()
        constLayout.showView()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            upcomingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TRENDING) }
            nowplayingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TOP_RATED) }
            topText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ONTHEAIR) }
            popularText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.POPULAR) }
            trendingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.AIRINGTODAY) }
            familyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.FAMILYTYPE) }
            animeText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ANIMETYPE) }
            dramaText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.DRAMATYPE) }
            comedyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.COMEDY) }
            historyText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.HISTORY) }
            mysteryText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.MYSTERY) }
            westernText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.WESTERN) }
        }
    }
//
//    private fun openDialogSheet(item: SeriesUi) {
//        val bottomSheet = BottomSheetDialog(requireContext())
//        bottomSheet.setContentView(R.layout.details_item_dialog)
//        val movieImage = bottomSheet.findViewById<ImageView>(R.id.poster_image)
//        val movieDes = bottomSheet.findViewById<TextView>(R.id.overview_text)
//        val movieMore = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
//        val movieTitle = bottomSheet.findViewById<TextView>(R.id.title_text)
//        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_detail_btn)
//        val rating = bottomSheet.findViewById<TextView>(R.id.rating_text)
//        cancelBtn?.setOnDownEffectClickListener { bottomSheet.dismiss() }
//        Picasso.get().load(Utils.IMAGE_PATH + item.posterPath).into(movieImage)
//        rating?.text = item.voteAverage.toString()
//        movieDes?.text = item.overview
//        movieTitle?.text = item.name
//        movieMore?.setOnDownEffectClickListener {
//            viewModel.launchTvDetails(item)
//            bottomSheet.dismiss()
//        }
//        bottomSheet.setCancelable(true)
//        bottomSheet.show()
//
//    }

    override fun onItemClick(movie: SeriesUi) = viewModel.launchTvDetails(movie)

    override fun onLongItemClick(movie: SeriesUi) {
        viewModel.saveMovie(movie)
        makeToast("Фильм (${movie.originalName}) Сохранён", requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view).showView()
    }
}

