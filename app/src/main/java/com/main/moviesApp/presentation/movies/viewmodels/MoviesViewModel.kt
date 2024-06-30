package com.main.moviesApp.presentation.movies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.models.MovieResponse
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.data.source.network.response.Response
import com.main.moviesApp.domain.usecase.cases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<PagingData<Movie>>>(Resource.Loading(null))
    val movies: StateFlow<Resource<PagingData<Movie>>> = _movies



     fun fetchMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke(param =  Unit, coroutineScope = viewModelScope)
                .collect { resource -> _movies.value = resource }
        }
    }
}