package pe.santillan.rappi.movies.util

import android.net.Uri
import androidx.fragment.app.Fragment
import pe.santillan.rappi.data.domain.Token
import pe.santillan.rappi.movies.BuildConfig
import pe.santillan.rappi.movies.MoviesApplication
import pe.santillan.rappi.movies.ui.di.component.AppComponent

fun buildAuthUri(it: Token): Uri {
    return Uri.parse(BuildConfig.THE_MOVIE_DB_WEB_BASE_URL + "authenticate/" + it.token)
}

fun Fragment.app(): AppComponent {
    return requireActivity().let {
        (it.application as MoviesApplication).appComponent
    }
}
