package pe.santillan.rappi.movies.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.data.repository.movie.MovieRepository
import java.util.function.Consumer

class MoviesViewModel(
    private val movieRepository: MovieRepository,
    private val uiThread: Scheduler = AndroidSchedulers.mainThread(),
    private val workThread: Scheduler = Schedulers.io(),
) : ViewModel() {
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun getTopRatedMovies(subscriber: Consumer<List<Movie>>) {
        compositeDisposable.add(movieRepository.getTopRatedMovies()
            .observeOn(workThread)
            .subscribeOn(uiThread)
            .subscribe { subscriber.accept(it) })
    }

    fun getPopularMovies(subscriber: Consumer<List<Movie>>) {
        compositeDisposable.add(movieRepository.getPopularMovies()
            .observeOn(workThread)
            .subscribeOn(uiThread)
            .subscribe { subscriber.accept(it) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}