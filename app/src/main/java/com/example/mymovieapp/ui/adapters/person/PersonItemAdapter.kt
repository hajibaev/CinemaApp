package com.example.mymovieapp.ui.adapters.person

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.ui.adapters.click.RvClickListener
import com.example.mymovieapp.ui.adapters.diffCallBack.PersonListDiffCallback
import com.example.mymovieapp.ui.adapters.view_holdeer.RvViewHolder

class PersonItemAdapter(
    private val listener: RvClickListener<PersonPresentation>,
) : RecyclerView.Adapter<RvViewHolder>() {

    var personsList = listOf<PersonPresentation>()
        set(value) {
            val callback = PersonListDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder =
        RvViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.object_portrait_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bindPerson(person = personsList[position])
        holder.view.setOnClickListener {
            listener.onItemClick(item = personsList[position])

        }
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation
                (holder.itemView.context, R.anim.animation)
        )
    }

    override fun getItemCount() = personsList.size
}
