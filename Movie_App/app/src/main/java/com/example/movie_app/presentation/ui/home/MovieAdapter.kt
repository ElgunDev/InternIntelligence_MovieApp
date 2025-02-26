package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.home.movies.Movie
import com.example.movie_app.databinding.ItemMoviesBinding

class MovieAdapter(
    private val onItemClick: (Movie)->Unit
):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
           return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (diffUtil.currentList.isEmpty()) {
            holder.bind(null)
        } else {
            holder.bind(diffUtil.currentList[position])
        }
    }

    inner class MovieViewHolder(val binding: ItemMoviesBinding):ViewHolder(binding.root){
        fun bind(movie: Movie?){
            if ( movie==null) {
                binding.txtMoveNotFound.visibility = View.VISIBLE
                binding.txtMoveNotFound.bringToFront()
                binding.imgMovie.visibility = View.GONE
            } else {
                binding.txtMoveNotFound.visibility = View.GONE
                binding.imgMovie.visibility = View.VISIBLE

                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                    .into(binding.imgMovie)

                binding.root.setOnClickListener {
                    onItemClick(movie)
                }
            }
          }
        }

    override fun getItemViewType(position: Int): Int {
        return if (diffUtil.currentList.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_MOVIE
    }
    companion object {
        private const val VIEW_TYPE_MOVIE = 1
        private const val VIEW_TYPE_EMPTY = 0
    }
    fun submitList( list: List<Movie>){
        diffUtil.submitList(list)
    }
    }

