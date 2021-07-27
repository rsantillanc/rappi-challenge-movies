package pe.santillan.rappi.movies.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.santillan.rappi.data.repository.auth.AuthRepository
import pe.santillan.rappi.data.repository.movie.MovieRepository
import pe.santillan.rappi.movies.vm.MoviesViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository,
    private val authRepository: AuthRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(movieRepository, authRepository) as T
    }
}