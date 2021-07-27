package pe.santillan.rappi.movies.ui.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.movies.ui.adapter.vh.MovieViewHolder

@Suppress("UNCHECKED_CAST")
class ListMovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()), Filterable {

    val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitMovies(list: List<Movie>) {
        if (movies.isNullOrEmpty())
            movies.addAll(list)
        submitList(list)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Movie>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(movies)
                } else {
                    for (item in movies) {
                        if (item.title.contains(constraint)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.let {
                    submitMovies(it.values as MutableList<Movie>)
                }
            }
        }
    }
}

internal class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
