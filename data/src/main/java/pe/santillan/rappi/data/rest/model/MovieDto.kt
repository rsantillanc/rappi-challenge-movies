package pe.santillan.rappi.data.rest.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("poster_path") val posterPath: String,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("original_title") val title: String,
    @field:SerializedName("popularity") val popularity: Double,
    @field:SerializedName("vote_average") val voteAverage: Float,
    @field:SerializedName("vote_count") val voteCount: Int,
    @field:SerializedName("release_date") val releaseDate: String,
)
