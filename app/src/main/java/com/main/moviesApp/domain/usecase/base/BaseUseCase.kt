package com.main.moviesApp.domain.usecase.base

import androidx.lifecycle.LiveData
import com.main.moviesApp.data.source.network.response.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T: Any?, R: Any?> {
    suspend operator fun invoke(param: T,coroutineScope: CoroutineScope): Flow<Resource<R>>
}