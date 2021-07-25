package pe.santillan.rappi.data.mapper

import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.data.rest.model.MovieDto

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