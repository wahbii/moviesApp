package com.main.moviesApp.utils.di


import com.main.moviesApp.data.repository.MoviesRepositoryImpl
import com.main.moviesApp.data.source.network.api.ApiService
import com.main.moviesApp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun providesMoviesRepo(apiService: ApiService) : MoviesRepository = MoviesRepositoryImpl(apiService)
}