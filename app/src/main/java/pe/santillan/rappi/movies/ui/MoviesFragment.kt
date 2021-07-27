package pe.santillan.rappi.movies.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import pe.santillan.rappi.movies.R
import pe.santillan.rappi.movies.databinding.FragmentMoviesBinding
import pe.santillan.rappi.movies.ui.adapter.ListMovieAdapter
import pe.santillan.rappi.movies.util.app
import pe.santillan.rappi.movies.vm.MoviesViewModel
import pe.santillan.rappi.movies.vm.factory.MoviesViewModelFactory
import javax.inject.Inject

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    private val moviesViewModel: MoviesViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        app().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        binding = bind(inflater, group)
        setupUi()
        subscribeUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.primaryColor)
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.verifyAccessStatus()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movies_menu, menu)

        val listMovieAdapter = binding.popularList.adapter as ListMovieAdapter
        val topRatedList = binding.topRatedList.adapter as ListMovieAdapter

        requireActivity().apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            (menu.findItem(R.id.action_search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
                setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        listMovieAdapter.filter.filter(query)
                        topRatedList.filter.filter(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        listMovieAdapter.filter.filter(query)
                        topRatedList.filter.filter(query)
                        return true
                    }
                })
            }
        }
    }

    private fun setupUi() {
        setHasOptionsMenu(true)
        with(binding) {
            topRatedList.adapter = ListMovieAdapter()
            popularList.adapter = ListMovieAdapter()
        }
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
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
            (binding.popularList.adapter as? ListMovieAdapter)?.submitMovies(popularMovies)
        }
    }

    private fun subscribeToFetchTopRatedMovies() {
        moviesViewModel.getTopRatedMovies { ratedMovies ->
            (binding.topRatedList.adapter as? ListMovieAdapter)?.submitMovies(ratedMovies)
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

    private fun bind(inflater: LayoutInflater, group: ViewGroup?) = FragmentMoviesBinding.bind(
        inflater.inflate(R.layout.fragment_movies, group, false))
}