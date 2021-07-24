package pe.santillan.rappi.data.rest.api

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import pe.santillan.rappi.data.rest.model.MovieDto
import pe.santillan.rappi.data.rest.model.SessionDto
import pe.santillan.rappi.data.rest.model.TokenDto
import pe.santillan.rappi.data.rest.model.WrapResultsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TheMoviesApi {

    @GET("authentication/token/new")
    fun authenticateNewToken(): Single<TokenDto>

    @POST("authentication/session/new")
    fun authenticationNewSession(@Body body: JsonObject): Single<SessionDto>

    @GET("movie/popular")
    fun getMoviesByPopularity(): Single<WrapResultsDto<MovieDto>>

    @GET("movie/top_rated")
    fun getMoviesByTopRated(): Single<WrapResultsDto<MovieDto>>
}