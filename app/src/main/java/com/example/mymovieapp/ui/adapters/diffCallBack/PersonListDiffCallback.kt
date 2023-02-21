package com.example.mymovieapp.ui.adapters.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.app.models.person.PersonPresentation

class PersonListDiffCallback(
    private val oldList: List<PersonPresentation>,
    private val newList: List<PersonPresentation>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}