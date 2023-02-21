package com.example.mymovieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.broadcast.myapplication.adapter.Item
import com.broadcast.myapplication.adapter.ItemFingerprint
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.app.base.BaseViewHolder
import com.example.mymovieapp.databinding.ObjectHorizontalBinding
import com.example.mymovieapp.app.utils.extensions.startSlideInLeftAnim
import com.example.mymovieapp.app.utils.extensions.toDp
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso

class TaskFingerprint : ItemFingerprint<ObjectHorizontalBinding, MovieModelAdapter> {

    override fun isRelativeItem(item: Item) = item is MovieModelAdapter

    override fun getLayoutId() = R.layout.object_horizontal

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ObjectHorizontalBinding, MovieModelAdapter> {
        val binding = ObjectHorizontalBinding.inflate(layoutInflater, parent, false)
        binding.root.layoutParams = binding.root.layoutParams.also {
            it.width = 280.toDp
        }
        return TaskViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<MovieModelAdapter>() {

        override fun areItemsTheSame(oldItem: MovieModelAdapter, newItem: MovieModelAdapter) =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(oldItem: MovieModelAdapter, newItem: MovieModelAdapter) =
            oldItem == newItem
    }

}

class TaskViewHolder(
    binding: ObjectHorizontalBinding,
) : BaseViewHolder<ObjectHorizontalBinding, MovieModelAdapter>(binding) {

    override fun onBind(item: MovieModelAdapter) {
        super.onBind(item)
        with(binding) {
            container.startSlideInLeftAnim()
            Picasso.get().load(Utils.IMAGE_PATH + item.posterPath).into(posterImage)
            val voteAverage = (item.voteAverage * 10.0)
            if (voteAverage.toInt() >= 70) {
                progressView.setProgressColorRes(R.color.greenPrimaryColor)
            } else if (voteAverage.toInt() in 51..69) {
                progressView.setProgressColorRes(R.color.yellow)
            } else {
                progressView.setProgressColorRes(R.color.red)
            }
            progressView.setProgress(voteAverage.toInt(), true)

        }
    }
}


data class MovieModelAdapter(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genre_ids") val actorsId: List<Int>,
    @SerializedName("id") val movieId: Int,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("title") val movieTitle: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val rating: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("video") val isHasVideo: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
) : Item