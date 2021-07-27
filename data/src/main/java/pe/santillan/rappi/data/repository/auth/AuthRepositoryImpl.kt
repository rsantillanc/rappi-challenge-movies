package pe.santillan.rappi.data.repository.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import pe.santillan.rappi.data.domain.Session
import pe.santillan.rappi.data.domain.Token
import pe.santillan.rappi.data.mapper.asDomainModel
import pe.santillan.rappi.data.rest.api.KEY_REQUEST_TOKEN
import pe.santillan.rappi.data.rest.api.TheMoviesApi
import pe.santillan.rappi.data.util.TAG
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val moviesApi: TheMoviesApi,
    private val preferences: SharedPreferences,
) : AuthRepository {

    private val prefKey = "$TAG + #SESSION_KEY"

    override fun createToken(): Single<Token> {
        return moviesApi.authenticateNewToken().map { it.asDomainModel() }
    }

    override fun createSession(token: String): Single<Session> {
        return moviesApi.authenticationNewSession(JsonObject().apply {
            addProperty(KEY_REQUEST_TOKEN, token)
        }).map { it.asDomainModel() }
    }

    override fun saveSession(session: Session) {
        preferences.edit {
            putString(prefKey, session.session)
        }
    }

    override fun session(): Session = preferences.getString(prefKey, "").let {
        if (it.isNullOrEmpty()) {
            Session(false, "")
        } else {
            Session(true, it)
        }
    }
}