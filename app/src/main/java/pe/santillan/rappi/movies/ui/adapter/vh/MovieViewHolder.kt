package pe.santillan.rappi.movies.ui.adapter.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.movies.R
import pe.santillan.rappi.movies.databinding.ListItemMovieBinding

open class MovieViewHolder private constructor(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {
        binding.movie = item
    }

    companion object {
        fun from(group: ViewGroup) = MovieViewHolder(
            ListItemMovieBinding.bind(LayoutInflater.from(group.context).inflate(
                R.layout.list_item_movie,
                group,
                false
            ))
        )
    }
}