package com.example.mymovieapp.ui.adapters.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.utils.extensions.startItemAnim
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.diffCallBack.TvDiffCallBack
import com.example.mymovieapp.ui.adapters.view_holdeer.RvViewHolder

class TvAdapter(
    private val objectViewType: Int,
    private val listener: RvClickListener<SeriesUi>
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
            GENRES_ITEM -> R.layout.object_portrait_item
            HORIZONTAL_TYPE -> R.layout.object_horizontal
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.view.setOnClickListener {
            try { listener.onItemClick(moviesList[position]) }
            catch (e: Exception) { holder.showErrorSnackbar("Movies not ready yet") }
        }
        holder.view.setOnLongClickListener {
            listener.onLongClick(moviesList[position])
            true
        }
        try { holder.bindTvMovie(tv = moviesList[position])
        } catch (e: Exception) { }
        holder.itemView.startItemAnim()
    }

    override fun getItemCount() = moviesList.size

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == PORTRAIT_TYPE) {
            PORTRAIT_TYPE
        } else if (objectViewType == HORIZONTAL_TYPE) {
            HORIZONTAL_TYPE
        } else GENRES_ITEM
    }

    companion object {
        const val PORTRAIT_TYPE = 0
        const val HORIZONTAL_TYPE = 1
        const val GENRES_ITEM = 2
    }
}
