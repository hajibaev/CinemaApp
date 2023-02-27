package com.example.mymovieapp.ui.adapters.view_holdeer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.cloud.utils.Utils
import com.example.data.cloud.utils.Utils.IMAGE_PATH
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.person.CastUi
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.models.person.PersonPresentation
import com.example.mymovieapp.app.utils.extensions.hideView
import com.example.mymovieapp.app.utils.extensions.showRoundedImage
import com.example.mymovieapp.databinding.FragmentPersonDetailsBinding
import com.example.mymovieapp.databinding.StorageItemBinding
import com.example.ui_core.custom.snackbar.SnackBar
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class RvViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val castImage = view.findViewById<ImageView>(R.id.personImageCast)
    private val castName = view.findViewById<TextView>(R.id.cast_name)
    private val castText = view.findViewById<TextView>(R.id.cast_text)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)

    fun bindMovie(movie: MovieUi) = bind(movie.posterPath, movie.voteAverage)
    fun bindCrew(crewUi: CrewUi) =
        bindCast(crewUi.profile_path, crewUi.original_name, crewUi.known_for_department)

    fun bindCast(castUi: CastUi) = bindCast(castUi.profilePath, castUi.name, castUi.character)
    fun bindTvMovie(tv: SeriesUi) = bind(tv.posterPath, tv.voteAverage)

    fun bindPerson(person: PersonPresentation) =
        bindPerson(person.profile_path, popularity = person.popularity)

    fun showErrorSnackbar(message: String) =
        SnackBar
            .Builder(view)
            .error()
            .message(message)
            .build()
            .show()

    fun bindCast(posterPath: String?, name: String, cast_text: String) {
        if (posterPath != null) {
            view.context.showRoundedImage(
                imageUrl = IMAGE_PATH + posterPath,
                imageView = castImage
            )
        }
        castName.text = name
        castText.text = cast_text
    }


    private fun bind(posterPath: String?, popularity: Double) {
        view.context.showRoundedImage(
            imageUrl = IMAGE_PATH + posterPath,
            imageView = image
        )
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

    private fun bindPerson(posterPath: String?, popularity: Double) {
        view.context.showRoundedImage(
            imageUrl = IMAGE_PATH + posterPath,
            imageView = image
        )
        if (popularity.toInt() >= 70) {
            progressView.setProgressColorRes(R.color.greenPrimaryColor)
        } else if (popularity.toInt() in 51..69) {
            progressView.setProgressColorRes(R.color.yellow)
        } else {
            progressView.setProgressColorRes(R.color.red)
        }
        progressView.setProgress(popularity.toInt(), true)
    }
}
