package com.example.mymovieapp.ui.adapters.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.StorageItemBinding
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClick
import com.example.mymovieapp.app.utils.extensions.showRoundedImage
import com.example.ui_core.custom.snackbar.SnackBar
import com.squareup.picasso.Picasso

class MovieStorageAdapter(
    private val context: Context,
    private val listener: RvClickListener<MovieUi>,
) : ListAdapter<MovieUi, MovieStorageAdapter.ViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.storage_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnDownEffectClick {
            listener.onItemClick(getItem(position))
        }
        holder.itemView.setOnClickListener {
            listener.onLongClick(getItem(position))
        }
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = StorageItemBinding.bind(itemView)
        fun bind(movie: MovieUi) = movie.apply {
            with(binding) {
                buttonBookmark.setOnDownEffectClick {
                    it.isClickable = false
                    listener.onLongClick(getItem(adapterPosition))
                }
                storage.setOnClickListener {
                    try { listener.onItemClick(getItem(adapterPosition))
                    } catch (e: Exception) { showErrorSnackbar("You have already deleted this movie") } }
                votecount.text = String.format(context.resources.getString(R.string.movieStorage_voteCont), voteCount.toString())
                textGenres.text = originalLanguage
                textTitle.text = originalTitle
                textReleaseDate.text = releaseDate
                voteaverage.rating = voteAverage.toFloat()
                view.context.showRoundedImage(
                    imageUrl = Utils.IMAGE_PATH + posterPath,
                    imageView = imagePoster
                )
            }
        }

        fun showErrorSnackbar(message: String) =
            SnackBar
                .Builder(binding.root)
                .error()
                .message(message)
                .build()
                .show()
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {

    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) =
        oldItem.movieId == newItem.movieId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) =
        oldItem == newItem

}

