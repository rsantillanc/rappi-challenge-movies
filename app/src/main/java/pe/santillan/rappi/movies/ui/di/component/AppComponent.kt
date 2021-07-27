package pe.santillan.rappi.movies.ui.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pe.santillan.rappi.movies.ui.MoviesFragment
import pe.santillan.rappi.movies.ui.di.module.ApiServiceModule
import pe.santillan.rappi.movies.ui.di.module.CacheModule
import pe.santillan.rappi.movies.ui.di.module.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiServiceModule::class, CacheModule::class, RepositoryModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(moviesFragment: MoviesFragment)
}