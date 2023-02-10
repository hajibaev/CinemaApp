package com.example.mymovieapp.ui.adapters.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.StorageItemBinding
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.diffCallBack.TvDiffCallBack
import com.squareup.picasso.Picasso

class TvStorageAdapter(
    private val context: Context,
    private val listener: RecyclerFavOnClickListener
) : ListAdapter<SeriesUi, TvStorageAdapter.ViewHolder>(TvDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.storage_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onTvClearItemClick(getItem(position))
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
        holder.bind(getItem(position))
    }

    interface RecyclerFavOnClickListener {
        fun onItemClick(seriesUi: SeriesUi)
        fun onTvClearItemClick(seriesUi: SeriesUi)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = StorageItemBinding.bind(itemView)
        fun bind(movie: SeriesUi) = movie.apply {
            with(binding) {
                Picasso.get().load(Utils.IMAGE_PATH + posterPath).into(imagePoster)
                buttonBookmark.setOnClickListener {
                    listener.onTvClearItemClick(getItem(adapterPosition))
                }
                storage.setOnClickListener {
                    listener.onItemClick(getItem(adapterPosition))
                }
                votecount.text = String.format(
                    context.resources.getString(R.string.movieStorage_voteCont),
                    voteCount.toString()
                )
                textGenres.text = originalLanguage
                textTitle.text = originalName
                textReleaseDate.text = firstAirDate
                voteaverage.rating = voteAverage.toFloat()
            }
        }
    }
}

class TvDiffCallBack : DiffUtil.ItemCallback<SeriesUi>() {

    override fun areItemsTheSame(oldItem: SeriesUi, newItem: SeriesUi) =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SeriesUi, newItem: SeriesUi) =
        oldItem == newItem

}

