package com.example.mymovieapp.ui.adapters.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.ui.adapters.diffCallBack.TvDiffCallBack
import com.example.mymovieapp.ui.adapters.view_holdeer.RvViewHolder

class TvAdapter(
    private val objectViewType: Int,
    private val listener: RecyclerOnClickListener
) : RecyclerView.Adapter<RvViewHolder>() {

    var moviesList = listOf<SeriesUi>()
        set(value) {
            val callback = TvDiffCallBack(moviesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val layout = when (viewType) {
            PORTRAIT_TYPE -> R.layout.object_portrait_item
            HORIZONTAL_TYPE -> R.layout.object_horizontal
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onItemClick(moviesList[position])
        }
        holder.view.setOnLongClickListener {
            listener.onLongItemClick(moviesList[position])
            true
        }
        holder.bindTvMovie(tv = moviesList[position])
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.item_anim
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == PORTRAIT_TYPE) {
            PORTRAIT_TYPE
        } else HORIZONTAL_TYPE
    }

    companion object {
        const val PORTRAIT_TYPE = 1
        const val HORIZONTAL_TYPE = 0
    }

    interface RecyclerOnClickListener {
        fun onItemClick(movie: SeriesUi)
        fun onLongItemClick(movie: SeriesUi)
    }
}
