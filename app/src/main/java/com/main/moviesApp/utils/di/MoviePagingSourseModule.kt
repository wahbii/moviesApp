package com.main.moviesApp.utils.di

import com.main.moviesApp.domain.utils.pagging.MoviePagingSource
import com.main.moviesApp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MoviePagingSourseModule {

    @Provides
    fun providesMoviePagingSourse(moviesRepository: MoviesRepository) : MoviePagingSource = MoviePagingSource(moviesRepository)
}