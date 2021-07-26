package pe.santillan.rappi.data.mapper

import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.data.domain.Session
import pe.santillan.rappi.data.domain.Token
import pe.santillan.rappi.data.rest.model.MovieDto
import pe.santillan.rappi.data.rest.model.SessionDto
import pe.santillan.rappi.data.rest.model.TokenDto

@JvmName("asMovieListDomainModel")
fun List<MovieDto>.asDomainModel(advice: String = "popular"): List<Movie> {
    return map {
        Movie(
            id = it.id,
            posterPath = it.posterPath,
            overview = it.overview,
            title = it.title,
            popularity = it.popularity,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            releaseDate = it.releaseDate,
            adviceType = advice
        )
    }
}

fun TokenDto.asDomainModel(): Token {
    return Token(isSuccess, expiresAt, token)
}

fun SessionDto.asDomainModel(): Session {
    return Session(isSuccess, session)
}