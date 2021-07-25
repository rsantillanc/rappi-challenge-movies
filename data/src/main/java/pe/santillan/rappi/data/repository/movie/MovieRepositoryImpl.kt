package pe.santillan.rappi.data.repository.movie

import io.reactivex.rxjava3.core.Observable
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.data.mapper.asDomainModel
import pe.santillan.rappi.data.rest.api.TheMoviesApi

class MovieRepositoryImpl(private val moviesApi: TheMoviesApi) : MovieRepository {
    override fun getTopRatedMovies(): Observable<List<Movie>> {
        return moviesApi.getMoviesByTopRated().map { it.results.asDomainModel() }.toObservable()
    }

    override fun getPopularMovies(): Observable<List<Movie>> {
        return moviesApi.getMoviesByPopularity().map { it.results.asDomainModel() }.toObservable()
    }
}