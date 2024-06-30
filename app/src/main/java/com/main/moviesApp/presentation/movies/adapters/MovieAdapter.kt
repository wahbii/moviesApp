package com.main.moviesApp.presentation.movies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.main.moviesApp.BuildConfig
import com.main.moviesApp.R
import com.main.moviesApp.data.models.Movie
import com.main.moviesApp.databinding.MovieItemLayoutBinding

class MovieAdapter (private val context: Context) :
     PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    var onActionClickCallback: ((id: String?)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(view,onActionClickCallback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(var  binding: MovieItemLayoutBinding ,var callBackAction :((id: String?)->Unit)?): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {

            binding.title.text = movie.original_title
            binding.date.text = movie.release_date
            binding.desc.text = movie.overview
            Glide.with(binding.root.context)
                .load("${BuildConfig.BASE_URL_IMAGE}${movie.poster_path}")
                .apply( RequestOptions().placeholder(R.drawable.ic_img_default)
                    .error(R.drawable.ic_img_default))
                .centerCrop()
                .into(binding.img)
            binding.root.setOnClickListener {
                callBackAction?.invoke(movie.id.toString())
            }

        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}