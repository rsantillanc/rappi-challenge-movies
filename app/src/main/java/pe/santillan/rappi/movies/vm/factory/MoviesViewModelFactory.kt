package pe.santillan.rappi.movies.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.santillan.rappi.data.repository.movie.MovieRepository
import pe.santillan.rappi.movies.vm.MoviesViewModel

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(movieRepository) as T
    }
}