package com.example.mymovieapp.ui.adapters.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.models.movie.MovieUi

class DiffCallBack(
    private val oldList: List<MovieUi>,
    private val newList: List<MovieUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].movieId == newList[newItemPosition].movieId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}