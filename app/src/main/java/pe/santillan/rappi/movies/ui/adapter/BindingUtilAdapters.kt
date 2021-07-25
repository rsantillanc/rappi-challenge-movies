package pe.santillan.rappi.movies.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.movies.R

@BindingAdapter("fetchMovieImage")
fun ImageView.fetchMovieImage(movie: Movie) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(this).load(movie.posterPath)
        .placeholder(circularProgressDrawable)
        .into(this)
}

@BindingAdapter("srcAdviceIcon")
fun ImageView.srcAdviceIcon(movie: Movie) {
    setImageResource(if (movie.adviceType == "rated") {
        R.drawable.ic_rated
    } else {
        R.drawable.ic_popular
    })
}

@BindingAdapter("setAdviceValue")
fun TextView.setAdviceValue(movie: Movie) {
    text = if (movie.adviceType == "rated") {
        "${movie.voteCount}"
    } else {
        "${movie.popularity}"
    }
}