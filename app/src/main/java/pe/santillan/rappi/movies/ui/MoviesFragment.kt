package pe.santillan.rappi.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import pe.santillan.rappi.data.BuildConfig
import pe.santillan.rappi.data.repository.movie.MovieRepositoryImpl
import pe.santillan.rappi.data.rest.api.ApiServiceBuilder
import pe.santillan.rappi.data.rest.api.TheMoviesApi
import pe.santillan.rappi.movies.R
import pe.santillan.rappi.movies.databinding.FragmentMoviesBinding
import pe.santillan.rappi.movies.ui.adapter.ListMovieAdapter
import pe.santillan.rappi.movies.vm.MoviesViewModel
import pe.santillan.rappi.movies.vm.factory.MoviesViewModelFactory

class MoviesFragment : Fragment() {
    val moviesApi = ApiServiceBuilder.create<TheMoviesApi>(BuildConfig.THE_MOVIE_DB_API_BASE_URL)
    val movieRepository = MovieRepositoryImpl(moviesApi)
    private val moviesViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(movieRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        // Inflate the layout for this fragment
        val binding = bind(inflater, group)
        setupUi(binding)
        subscribeUi(binding)
        return binding.root
    }

    private fun setupUi(binding: FragmentMoviesBinding) {
        with(binding) {
            topRatedList.adapter = ListMovieAdapter()
            popularList.adapter = ListMovieAdapter()
        }
    }

    private fun subscribeUi(binding: FragmentMoviesBinding) {
        moviesViewModel.getPopularMovies { popularMovies ->
            (binding.popularList.adapter as? ListMovieAdapter)?.submitList(popularMovies)
        }
        moviesViewModel.getTopRatedMovies { ratedMovies ->
            (binding.popularList.adapter as? ListMovieAdapter)?.submitList(ratedMovies)
        }
        moviesViewModel.error.observe(viewLifecycleOwner) { error ->
            Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun bind(
        inflater: LayoutInflater,
        group: ViewGroup?,

        ) = FragmentMoviesBinding.bind(inflater.inflate(
        R.layout.fragment_movies,
        group,
        false))
}