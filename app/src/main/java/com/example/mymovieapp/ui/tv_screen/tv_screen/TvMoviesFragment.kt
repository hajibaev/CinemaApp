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
import com.example.mymovieapp.ui.adapters.movie.TvAdapter
import com.example.mymovieapp.ui.hideView
import com.example.mymovieapp.ui.makeToast
import com.example.mymovieapp.ui.see_all_screen.SeeAllTvType
import com.example.mymovieapp.ui.showView
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

    private val trendingRv by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }

    private val topRatedRv by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }

    private val onTheAirRv by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }

    private val popularRv by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }

    private val airingTodayRv by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val familyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val dramaMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val animeMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val comedyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val historyMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val mysteryMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private val westernMoviesAdapter by lazy {
        TvAdapter(TvAdapter.HORIZONTAL_TYPE, this)
    }
    private var isFavorite = false


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
        lifecycleScope.launchWhenResumed {
            tvTrending.collectLatest {
                trendingRv.moviesList = it.results
                uiVisibility()
            }
        }
        lifecycleScope.launchWhenResumed {
            tvTopRated.collectLatest {
                topRatedRv.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            tvOnTheAir.collectLatest {
                onTheAirRv.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            tvPopular.collectLatest {
                popularRv.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            tvAiringToday.collectLatest {
                airingTodayRv.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            familyMovies.collectLatest {
                familyMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            anime.collectLatest {
                animeMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            dramaMovies.collectLatest {
                dramaMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            comedyMovies.collectLatest {
                comedyMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            historyMovies.collectLatest {
                historyMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            westernMovies.collectLatest {
                westernMoviesAdapter.moviesList = it.results
            }
        }
        lifecycleScope.launchWhenResumed {
            mysteryMovies.collectLatest {
                mysteryMoviesAdapter.moviesList = it.results
            }
        }
        error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.stopShimmer()
            requireBinding().constLayout.showView()
        }
    }

    private fun uiVisibility() = with(requireBinding()) {
        progress.visibility = View.INVISIBLE
        textView.visibility = View.VISIBLE
        textView2.visibility = View.VISIBLE
        textView3.visibility = View.VISIBLE
        textView4.visibility = View.VISIBLE
        textView5.visibility = View.VISIBLE
        textView6.visibility = View.VISIBLE
        textView7.visibility = View.VISIBLE
        textView8.visibility = View.VISIBLE
        textView9.visibility = View.VISIBLE
        textView10.visibility = View.VISIBLE
        textView11.visibility = View.VISIBLE
        textView12.visibility = View.VISIBLE
        nowplayingText.visibility = View.VISIBLE
        upcomingText.visibility = View.VISIBLE
        topText.visibility = View.VISIBLE
        trendingText.visibility = View.VISIBLE
        popularText.visibility = View.VISIBLE
        familyText.visibility = View.VISIBLE
        animeText.visibility = View.VISIBLE
        dramaText.visibility = View.VISIBLE
        comedyText.visibility = View.VISIBLE
        historyText.visibility = View.VISIBLE
        mysteryText.visibility = View.VISIBLE
        westernText.visibility = View.VISIBLE
        shimmerLayout.hideView()
        constLayout.showView()
    }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            upcomingText.setOnClickListener { launchTvType(SeeAllTvType.TRENDING) }
            nowplayingText.setOnClickListener { launchTvType(SeeAllTvType.TOP_RATED) }
            topText.setOnClickListener { launchTvType(SeeAllTvType.ONTHEAIR) }
            popularText.setOnClickListener { launchTvType(SeeAllTvType.POPULAR) }
            trendingText.setOnClickListener { launchTvType(SeeAllTvType.AIRINGTODAY) }
            familyText.setOnClickListener { launchTvType(SeeAllTvType.FAMILYTYPE) }
            animeText.setOnClickListener { launchTvType(SeeAllTvType.ANIMETYPE) }
            dramaText.setOnClickListener { launchTvType(SeeAllTvType.DRAMATYPE) }
            comedyText.setOnClickListener { launchTvType(SeeAllTvType.COMEDY) }
            historyText.setOnClickListener { launchTvType(SeeAllTvType.HISTORY) }
            mysteryText.setOnClickListener { launchTvType(SeeAllTvType.MYSTERY) }
            westernText.setOnClickListener { launchTvType(SeeAllTvType.WESTERN) }
        }
    }

    private fun openDialogSheet(item: SeriesUi) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.details_item_dialog)
        val movieImage = bottomSheet.findViewById<ImageView>(R.id.poster_image)
        val movieDes = bottomSheet.findViewById<TextView>(R.id.overview_text)
        val movieMore = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
        val movieTitle = bottomSheet.findViewById<TextView>(R.id.title_text)
        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_detail_btn)
        val rating = bottomSheet.findViewById<TextView>(R.id.rating_text)
        val addTv = bottomSheet.findViewById<ImageView>(R.id.add)
        cancelBtn?.setOnClickListener {
            bottomSheet.dismiss()
        }
        if (isFavorite) addTv?.setImageResource(R.drawable.ic_saved)
        else addTv?.setImageResource(R.drawable.ic_save_vector)
        Picasso.get().load(Utils.IMAGE_PATH + item.posterPath).into(movieImage)
        rating?.text = item.voteAverage.toString()
        movieDes?.text = item.overview
        movieTitle?.text = item.name

        addTv?.setOnClickListener {
            if (isFavorite) {
                isFavorite = false
                addTv.setImageResource(R.drawable.ic_save_vector)
                viewModel.deleteTV(tvId = item.id)
            } else {
                isFavorite = true
                addTv.setImageResource(R.drawable.ic_saved)
                viewModel.saveMovie(item)
            }
        }
        movieMore?.setOnClickListener {
            viewModel.launchTvDetails(item)
            bottomSheet.dismiss()
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()

    }

    override fun onItemClick(movie: SeriesUi) = openDialogSheet(item = movie)

    override fun onLongItemClick(movie: SeriesUi) {
        viewModel.saveMovie(movie)
        makeToast(
            "Фильм (${movie.originalName}) Сохранён",
            context = requireContext()
        )
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}

