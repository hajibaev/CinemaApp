package com.example.mymovieapp.ui.adapters.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.utils.Utils
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.movie.CastUi
import com.example.mymovieapp.app.utils.extensions.setOnDownEffectClick
import com.example.mymovieapp.app.utils.extensions.startSlideInLeftAnim
import com.example.mymovieapp.ui.adapters.diffCallBack.ActorsDiffCallBack
import com.squareup.picasso.Picasso

class ActorsAdapters(private val listener: RvClickListener) :
    RecyclerView.Adapter<ActorsAdapters.ViewHolder>() {

    var personsList = listOf<CastUi>()
        set(value) {
            val callback = ActorsDiffCallBack(personsList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.actors_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnDownEffectClick {
            listener.onPersonItemClick(personsList[position])
        }
        holder.bind(personsList[position])
        holder.itemView.startSlideInLeftAnim()
    }

    override fun getItemCount(): Int = personsList.size

    interface RvClickListener {
        fun onPersonItemClick(person: CastUi)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = com.example.mymovieapp.databinding.ActorsItemBinding.bind(itemView)
        fun bind(cast: CastUi) = cast.apply {
            with(binding) {
                if (cast.profilePath != null) {
                    Picasso.get().load(Utils.IMAGE_PATH + profilePath).into(personImageCast)
                }
                name.text = cast.name
                castText.text = cast.character
            }
        }
    }
}





