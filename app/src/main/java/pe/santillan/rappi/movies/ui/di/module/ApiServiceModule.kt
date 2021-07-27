package pe.santillan.rappi.movies.ui.di.module

import dagger.Module
import dagger.Provides
import pe.santillan.rappi.data.rest.api.ApiServiceBuilder
import pe.santillan.rappi.data.rest.api.TheMoviesApi

@Module
class ApiServiceModule {

    @Provides
    fun provideTheMoviesApiService(): TheMoviesApi {
        return ApiServiceBuilder.create()
    }
}