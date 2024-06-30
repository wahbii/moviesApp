package com.main.moviesApp.data.repository

import com.main.moviesApp.data.source.network.api.ApiService
import com.main.moviesApp.data.source.network.response.NetworkResponse
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.models.MovieResponse
import com.main.moviesApp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(val apiService: ApiService) : MoviesRepository {


    override suspend fun getMovies(page: Int): Resource<MovieResponse> {
        return when (val response =
            apiService.getMovies(page)) {
            is NetworkResponse.Success -> {
                return Resource.Success(response.body!!)
            }
            is NetworkResponse.ApiError -> {
                Resource.Error(response.code, "${response.body.message}")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(2000, "${response.error}")
            }
            else -> {
                Resource.Error(1000, "$response")
            }
        }
    }

    override suspend fun getMoviesDetails(id: String): Resource<Movie> {
        return when (val response =
            apiService.getMovieDetail(id)) {
            is NetworkResponse.Success -> {
                return Resource.Success(response.body!!)
            }
            is NetworkResponse.ApiError -> {
                Resource.Error(response.code, "${response.body.message}")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(2000, "${response.error}")
            }
            else -> {
                Resource.Error(1000, "$response")
            }
        }
    }
}