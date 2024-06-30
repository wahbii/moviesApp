package com.main.moviesApp.domain.usecase.cases

import androidx.paging.PagingData
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.domain.usecase.base.BaseUseCase

interface GetMovieDetailUseCase : BaseUseCase<Any, Movie> {
}