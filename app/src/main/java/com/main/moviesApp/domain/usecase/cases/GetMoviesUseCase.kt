package com.main.moviesApp.domain.usecase.cases

import androidx.paging.PagingData
import com.main.moviesApp.domain.usecase.base.BaseUseCase
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.models.MovieResponse

interface GetMoviesUseCase : BaseUseCase<Any, PagingData<Movie>> {
}