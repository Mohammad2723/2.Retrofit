package ebrahimi16153.github.com.a2retrofit.server

import ebrahimi16153.github.com.a2retrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("movies")
    fun moviesList(@Query("page") page: Int): Call<ResponseMoviesList>

    @GET("movies/{movie_id}")
    fun singleMovie(@Path("movie_id") id:Int):Call<ResponseSingleMovie>

    @GET("genres/{genre_id}/movies")
    fun movieListByGenres(@Path("genre_id") id: Int , @Query("page") page: Int):Call<ResponseMovieListByGenres>

    @POST("register")
    fun userRegister(@Body body:BodyUserRegister):Call<ResponseUserRegister>



}