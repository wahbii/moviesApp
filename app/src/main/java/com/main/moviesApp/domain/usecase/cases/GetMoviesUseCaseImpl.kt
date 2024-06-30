package com.main.moviesApp.domain.usecase.cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.domain.utils.pagging.MoviePagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(val moviePagingSource: MoviePagingSource): GetMoviesUseCase{
    override suspend fun invoke(param: Any,coroutineScope: CoroutineScope): Flow<Resource<PagingData<Movie>>> {


        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { moviePagingSource }
        ).flow.cachedIn(coroutineScope)
            .map { pagingData -> Resource.Success(pagingData) }
            .map { resource ->
                Resource.Loading<PagingData<Movie>>(null)
                resource
            };
    }
}