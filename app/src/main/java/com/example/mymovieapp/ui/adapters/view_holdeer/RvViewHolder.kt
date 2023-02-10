package com.example.mymovieapp.ui.adapters.view_holdeer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.server.Utils.IMAGE_PATH
import com.example.mymovieapp.R
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.person.PersonDetailsPresentation
import com.example.mymovieapp.models.person.PersonPresentation
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class RvViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)
    private val rating = view.findViewById<TextView>(R.id.rating_text)


    fun bindMovie(movie: MovieUi) {
        bind(movie.posterPath, movie.voteAverage)
    }

    fun bindTvMovie(tv: SeriesUi) {
        bind(tv.posterPath, tv.voteAverage)
    }

    fun bindTvType(movie: SeriesUi) {
        bindMovie(movie.posterPath, movie.voteAverage)
    }

    fun bindPerson(person: PersonPresentation) {
        bind(person.profile_path, popularity = person.popularity)
    }

    private fun bind(posterPath: String?, popularity: Double) {
        Picasso.get().load(IMAGE_PATH + posterPath).into(image)
        val voteAverage = (popularity * 10.0)
        if (voteAverage.toInt() >= 70) {
            progressView.setProgressColorRes(R.color.greenPrimaryColor)
        } else if (voteAverage.toInt() in 51..69) {
            progressView.setProgressColorRes(R.color.yellow)
        } else {
            progressView.setProgressColorRes(R.color.red)
        }
        progressView.setProgress(voteAverage.toInt(), true)
    }

    private fun bindMovie(posterPath: String?, popularity: Double) {
        Picasso.get().load(IMAGE_PATH + posterPath).into(image)
        rating.text = popularity.toString()
    }
}
