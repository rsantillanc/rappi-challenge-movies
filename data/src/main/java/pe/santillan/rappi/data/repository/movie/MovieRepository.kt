package pe.santillan.rappi.data.repository.movie

import io.reactivex.rxjava3.core.Observable
import pe.santillan.rappi.data.domain.Movie

interface MovieRepository {
    fun getTopRatedMovies(): Observable<List<Movie>>
    fun getPopularMovies(): Observable<List<Movie>>
}