package com.main.moviesApp.presentation.details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.main.moviesApp.BuildConfig
import com.main.moviesApp.MainActivity
import com.main.moviesApp.R
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.databinding.FragmentDetailBinding
import com.main.moviesApp.databinding.FragmentMoviesBinding
import com.main.moviesApp.presentation.details.viewmodel.DetailViewModel
import com.main.moviesApp.presentation.movies.viewmodels.MoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.log


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by activityViewModels()
    val args: DetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        args.idMovies?.let {
            getMovies(it)
        }
        return binding.root
    }


    private fun getMovies(idMovie: String) {
        lifecycleScope.launch {
            viewModel.apply {
                fetchMovieDetail(idMovie)
                movie.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Show loading state
                            binding.progress.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            resource.data?.let {
                                handleMovies(it)
                            }
                        }

                        is Resource.Error -> {
                            // Show error state
                            (requireActivity() as MainActivity).showDialog(context?.getString(R.string.dialog_message_error))
                        }
                    }
                }
            }


        }

    }

    private fun handleMovies(movie: Movie) {
        binding.apply {
            progress.visibility = View.GONE
            title.text = movie.original_title
            desc.text = movie.overview
            Glide.with(requireContext())
                .load("${BuildConfig.BASE_URL_IMAGE}${movie.poster_path}")
                .apply(
                    RequestOptions().placeholder(R.drawable.ic_img_default)
                        .error(R.drawable.ic_img_default)
                )
                .skipMemoryCache(true)
                .centerCrop()
                .into(img)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearData()
    }



}