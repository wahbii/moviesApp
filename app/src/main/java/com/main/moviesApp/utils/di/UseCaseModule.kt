package com.main.moviesApp.utils.di


import com.main.moviesApp.domain.utils.pagging.MoviePagingSource
import com.main.moviesApp.domain.repository.MoviesRepository
import com.main.moviesApp.domain.usecase.cases.GetMovieDetailUseCase
import com.main.moviesApp.domain.usecase.cases.GetMovieDetailUseCaseImpl
import com.main.moviesApp.domain.usecase.cases.GetMoviesUseCase
import com.main.moviesApp.domain.usecase.cases.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun providesUseCaseGetMovies( moviePagingSource: MoviePagingSource) : GetMoviesUseCase = GetMoviesUseCaseImpl(moviePagingSource)
    @Provides
    fun providesUseCaseGetMovieDetail( repository: MoviesRepository) : GetMovieDetailUseCase= GetMovieDetailUseCaseImpl(repository = repository)


   }