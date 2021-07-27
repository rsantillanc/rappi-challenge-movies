package pe.santillan.rappi.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pe.santillan.rappi.movies.R
import pe.santillan.rappi.movies.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View {
        binding = bind(inflater, group)
        binding.movie = args.movie
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.black_alpha_50)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> findNavController().popBackStack()
        else -> super.onOptionsItemSelected(item)
    }

    private fun bind(inflater: LayoutInflater, group: ViewGroup?) =
        FragmentMovieDetailBinding.bind(inflater.inflate(
            R.layout.fragment_movie_detail,
            group,
            false))
}
