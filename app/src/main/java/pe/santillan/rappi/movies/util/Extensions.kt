package pe.santillan.rappi.movies.util

import android.net.Uri
import pe.santillan.rappi.data.domain.Token
import pe.santillan.rappi.movies.BuildConfig

fun buildAuthUri(it: Token): Uri {
    return Uri.parse(BuildConfig.THE_MOVIE_DB_WEB_BASE_URL + "authenticate/" + it.token)
}