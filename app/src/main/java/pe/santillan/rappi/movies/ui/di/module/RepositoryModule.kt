package pe.santillan.rappi.movies.ui.di.module

import dagger.Binds
import dagger.Module
import pe.santillan.rappi.data.repository.auth.AuthRepository
import pe.santillan.rappi.data.repository.auth.AuthRepositoryImpl
import pe.santillan.rappi.data.repository.movie.MovieRepository
import pe.santillan.rappi.data.repository.movie.MovieRepositoryImpl

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}