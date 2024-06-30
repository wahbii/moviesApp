package  com.main.moviesApp.domain.repository

import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.models.MovieResponse


interface MoviesRepository {
    suspend fun getMovies( page: Int,) :Resource<MovieResponse>
    suspend fun getMoviesDetails(id:String) :Resource<Movie>
}