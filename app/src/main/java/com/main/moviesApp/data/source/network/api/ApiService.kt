package com.main.moviesApp.data.source.network.api

import com.main.moviesApp.data.source.network.response.NetworkResponse
import com.main.moviesApp.data.source.network.response.Response
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int,) : NetworkResponse<MovieResponse, Response.ErrorResponse>
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") page: String,) : NetworkResponse<Movie, Response.ErrorResponse>



}