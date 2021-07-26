package pe.santillan.rappi.movies.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import pe.santillan.rappi.data.BuildConfig
import pe.santillan.rappi.data.repository.auth.AuthRepository
import pe.santillan.rappi.data.repository.auth.AuthRepositoryImpl
import pe.santillan.rappi.data.repository.movie.MovieRepositoryImpl
import pe.santillan.rappi.data.rest.api.ApiServiceBuilder
import pe.santillan.rappi.data.rest.api.TheMoviesApi
import pe.santillan.rappi.movies.R
import pe.santillan.rappi.movies.databinding.FragmentMoviesBinding
import pe.santillan.rappi.movies.ui.adapter.ListMovieAdapter
import pe.santillan.rappi.movies.vm.MoviesViewModel
import pe.santillan.rappi.movies.vm.factory.MoviesViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var preference: SharedPreferences
    private val moviesApi = ApiServiceBuilder.create<TheMoviesApi>()
    private val movieRepository = MovieRepositoryImpl(moviesApi)
    private val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(moviesApi, preference)
    }
    private val moviesViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(movieRepository, authRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = requireActivity().getSharedPreferences(
            BuildConfig.LIBRARY_PACKAGE_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        // Inflate the layout for this fragment
        binding = bind(inflater, group)
        setupUi()
        subscribeUi()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.verifyAccessStatus()
    }

    private fun setupUi() {
        with(binding) {
            topRatedList.adapter = ListMovieAdapter()
            popularList.adapter = ListMovieAdapter()
        }
    }

    private fun subscribeUi() {
        subscribeToHandlerError()
        subscribeToRequestAuthorization()
        subscribeToFetchMovies()
    }

    private fun subscribeToFetchMovies() {
        moviesViewModel.readyToFetch.observe(viewLifecycleOwner) { ready ->
            if (ready) {
                subscribeToFetchPopularMovies()
                subscribeToFetchTopRatedMovies()
            }
        }
    }

    private fun subscribeToRequestAuthorization() {
        moviesViewModel.authUri.observe(viewLifecycleOwner) { uri ->
            uri?.let { showAuthorizationRequired(it) }
        }
    }

    private fun subscribeToHandlerError() {
        moviesViewModel.error.observe(viewLifecycleOwner) { error ->
            if (!error.isNullOrEmpty()) {
                Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun subscribeToFetchPopularMovies() {
        moviesViewModel.getPopularMovies { popularMovies ->
            (binding.popularList.adapter as? ListMovieAdapter)?.submitList(popularMovies)
        }
    }

    private fun subscribeToFetchTopRatedMovies() {
        moviesViewModel.getTopRatedMovies { ratedMovies ->
            (binding.popularList.adapter as? ListMovieAdapter)?.submitList(ratedMovies)
        }
    }

    private fun showAuthorizationRequired(uri: Uri) {
        Snackbar.make(binding.root, R.string.auth_required, Snackbar.LENGTH_INDEFINITE).setAction(
            R.string.request) {
            openWebPage(uri)
        }.show()
    }

    private fun openWebPage(webpage: Uri) {
        val intent = Intent(ACTION_VIEW, webpage)
        startActivity(intent)
    }

    private fun bind(
        inflater: LayoutInflater,
        group: ViewGroup?,

        ) = FragmentMoviesBinding.bind(inflater.inflate(
        R.layout.fragment_movies,
        group,
        false))
}