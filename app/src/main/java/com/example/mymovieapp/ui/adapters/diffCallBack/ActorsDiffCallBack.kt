package com.example.mymovieapp.ui.adapters.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.app.models.person.CastUi

class ActorsDiffCallBack(
    private val oldList: List<CastUi>,
    private val newList: List<CastUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}