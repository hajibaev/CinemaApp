package com.example.mymovieapp.ui.adapters.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.app.models.movie.SeriesUi

class TvDiffCallBack(
    private val oldList: List<SeriesUi>,
    private val newList: List<SeriesUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}