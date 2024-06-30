package com.main.moviesApp.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.domain.usecase.cases.GetMovieDetailUseCase
import com.main.moviesApp.domain.usecase.cases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel@Inject constructor(
    val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {
    private val _movie = MutableStateFlow<Resource<Movie>>(Resource.Loading(null))
    val movie: StateFlow<Resource<Movie>> = _movie



    fun fetchMovieDetail(id:String) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(id, coroutineScope = viewModelScope)
                .collect { resource -> _movie.value = resource }
        }
    }
}