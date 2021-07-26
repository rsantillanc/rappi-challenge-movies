package pe.santillan.rappi.data.repository.auth

import io.reactivex.rxjava3.core.Single
import pe.santillan.rappi.data.domain.Session
import pe.santillan.rappi.data.domain.Token

interface AuthRepository {
    fun createToken(): Single<Token>
    fun createSession(token: String): Single<Session>
    fun saveSession(session: Session)
    fun session(): Session
}