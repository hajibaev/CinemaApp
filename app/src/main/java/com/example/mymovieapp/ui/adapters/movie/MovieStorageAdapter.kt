package com.example.mymovieapp.ui.adapters.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.server.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.StorageItemBinding
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.diffCallBack.DiffCallBack
import com.squareup.picasso.Picasso

class MovieStorageAdapter(
    private val context: Context,
    private val listener: RecyclerFavOnClickListener
) : RecyclerView.Adapter<MovieStorageAdapter.ViewHolder>() {

    var moviesList = listOf<MovieUi>()
        set(value) {
            val callback = DiffCallBack(moviesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.storage_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
        holder.itemView.setOnLongClickListener {
            listener.onClearItemClick(moviesList[position])
            true
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(moviesList[position])
        }
    }

    interface RecyclerFavOnClickListener {
        fun onItemClick(movie: MovieUi)
        fun onClearItemClick(movie: MovieUi)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = StorageItemBinding.bind(itemView)
        fun bind(movie: MovieUi) = movie.apply {
            with(binding) {
                Picasso.get().load(Utils.IMAGE_PATH + posterPath).into(imagePoster)
                buttonBookmark.setOnClickListener {
                    listener.onClearItemClick(moviesList[adapterPosition])
                }
                storage.setOnClickListener {
                    listener.onItemClick(moviesList[adapterPosition])
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

    override fun getItemCount(): Int = moviesList.size
}
//
//class FavDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {
//
//    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) =
//        oldItem.movieId == newItem.movieId
//
//
//    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) =
//        oldItem == newItem
//
//}