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
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.diffCallBack.DiffCallBack
import com.squareup.picasso.Picasso

class MovieStorageAdapter(
    private val context: Context,
    private val listener: RecyclerFavOnClickListener
) : ListAdapter<MovieUi, MovieStorageAdapter.ViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.storage_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onMoviwItemClick(getItem(position))
        }
        holder.itemView.setOnClickListener {
            listener.onMovieClearItemClick(getItem(position))
        }
        holder.bind(getItem(position))
    }

    interface RecyclerFavOnClickListener {
        fun onMoviwItemClick(movieUi: MovieUi)
        fun onMovieClearItemClick(movieUi: MovieUi)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = StorageItemBinding.bind(itemView)
        fun bind(movie: MovieUi) = movie.apply {
            with(binding) {
                Picasso.get().load(Utils.IMAGE_PATH + posterPath).into(imagePoster)
                buttonBookmark.setOnClickListener {
                    listener.onMovieClearItemClick(getItem(adapterPosition))
                }
                storage.setOnClickListener {
                    listener.onMoviwItemClick(getItem(adapterPosition))
                }
                votecount.text = String.format(
                    context.resources.getString(R.string.movieStorage_voteCont),
                    voteCount.toString()
                )
                textGenres.text = originalLanguage
                textTitle.text = originalTitle
                textReleaseDate.text = releaseDate
                voteaverage.rating = voteAverage.toFloat()
            }
        }
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {

    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) =
        oldItem.movieId == newItem.movieId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) =
        oldItem == newItem

}

