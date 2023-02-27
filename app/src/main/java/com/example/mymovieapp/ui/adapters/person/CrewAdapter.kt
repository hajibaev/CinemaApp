package com.example.mymovieapp.ui.adapters.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.utils.extensions.startSlideInLeftAnim
import com.example.mymovieapp.ui.adapters.view_holdeer.RvViewHolder

class CrewAdapter(
    private val listener: RecyclerOnClickListener
) : RecyclerView.Adapter<RvViewHolder>() {

    var crewList = listOf<CrewUi>()
        set(value) {
            val callback = CrewDiffCallBack(crewList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.crew_item, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onItemClick(crewList[position])
        }
        holder.itemView.startSlideInLeftAnim()
        holder.bindCrew(crewList[position])
    }

    override fun getItemCount() = crewList.size

    interface RecyclerOnClickListener {
        fun onItemClick(crewUi: CrewUi)
    }
}

class CrewDiffCallBack(
    private val oldList: List<CrewUi>,
    private val newList: List<CrewUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}

