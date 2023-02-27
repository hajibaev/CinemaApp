package com.example.mymovieapp.ui.adapters.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.person.CastUi
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClick
import com.example.mymovieapp.app.utils.extensions.showRoundedImage
import com.example.mymovieapp.app.utils.extensions.startSlideInLeftAnim
import com.example.mymovieapp.ui.adapters.diffCallBack.ActorsDiffCallBack
import com.example.mymovieapp.ui.adapters.view_holdeer.RvViewHolder

class ActorsAdapters(private val listener: RvClickListener) :
    RecyclerView.Adapter<RvViewHolder>() {

    var personsList = listOf<CastUi>()
        set(value) {
            val callback = ActorsDiffCallBack(personsList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder =
        RvViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.actors_item, parent, false)
        )

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.itemView.setOnDownEffectClick {
            listener.onPersonItemClick(
                personsList[position],
            )
        }
        holder.bindCast(personsList[position])
        holder.itemView.startSlideInLeftAnim()
    }

    override fun getItemCount(): Int = personsList.size

    interface RvClickListener {
        fun onPersonItemClick(person: CastUi)
    }
}





