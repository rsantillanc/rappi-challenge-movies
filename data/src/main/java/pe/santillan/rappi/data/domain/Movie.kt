package pe.santillan.rappi.data.domain

data class Movie(
    val id: Long,
    val posterPath: String,
    val overview: String,
    val title: String,
    val popularity: Double,
    val voteAverage: Float,
    val voteCount: Int,
    val releaseDate: String,
    val adviceType: String,
)
