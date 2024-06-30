package com.main.moviesApp.domain.usecase.cases

import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(val repository: MoviesRepository):GetMovieDetailUseCase {
    override suspend fun invoke(param: Any,coroutineScope: CoroutineScope): Flow<Resource<Movie>> {
        return flowOf(repository.getMoviesDetails(param as String))
    }
}