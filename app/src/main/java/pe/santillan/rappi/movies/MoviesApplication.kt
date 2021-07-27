package pe.santillan.rappi.movies

import android.app.Application
import pe.santillan.rappi.movies.ui.di.component.AppComponent
import pe.santillan.rappi.movies.ui.di.component.DaggerAppComponent

open class MoviesApplication : Application() {
    val appComponent: AppComponent by lazy {
        appComponent()
    }

    private fun appComponent() = DaggerAppComponent.factory().create(this)
}