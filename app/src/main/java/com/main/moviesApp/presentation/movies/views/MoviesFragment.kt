package com.main.moviesApp.presentation.movies.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.moviesApp.MainActivity
import com.main.moviesApp.R
import com.main.moviesApp.data.source.network.response.Resource
import com.main.moviesApp.databinding.FragmentMoviesBinding
import com.main.moviesApp.presentation.movies.adapters.MovieAdapter
import com.main.moviesApp.presentation.movies.viewmodels.MoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch




const val PARAM_ID_DETAIL_KEY = "idMovies"
class MoviesFragment : Fragment() {

    lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by activityViewModels()
    lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, getOnBackPressedCallback())

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        getMovies()
    }

    private fun getMovies() {
        lifecycleScope.launch {
            viewModel.apply {
                fetchMovies()
                movies.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Show loading state
                            toggleProgress(true)
                        }
                        is Resource.Success -> {
                            toggleProgress(false)
                            resource.data?.let { movieAdapter.submitData(it) }
                        }
                        is Resource.Error -> {
                            toggleProgress(false)
                            (requireActivity() as MainActivity).showDialog(context?.getString(R.string.dialog_message_error))

                            // Show error state
                        }
                    }
                }
            }
        }

    }

    private fun initRecycleView() {
        val layoutManager = GridLayoutManager(requireContext(), 1)
        movieAdapter = MovieAdapter()
        movieAdapter.apply {
            onActionClickCallback = {
                val bundle = Bundle()
                bundle.putString(PARAM_ID_DETAIL_KEY,it)
                findNavController().navigate(R.id.moviesDetail ,bundle)
            }
        }
        binding.listMovies.apply {
            this.layoutManager = layoutManager
            this.adapter = movieAdapter
        }
    }

    private fun toggleProgress(visible: Boolean) {
        binding.progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun getOnBackPressedCallback() =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                  requireActivity().finish()
            }
        }


}