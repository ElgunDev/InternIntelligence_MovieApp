package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.home.movies.Movie
import com.example.movie_app.databinding.ItemPostersBinding

class PostersAdapter:RecyclerView.Adapter<PostersAdapter.PosterViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }


    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val binding = ItemPostersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PosterViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
      holder.bind(diffUtil.currentList[position])
    }

    inner class PosterViewHolder(private val binding:ItemPostersBinding):ViewHolder(binding.root){
        fun bind(movie: Movie){
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500" +movie.poster_path)
                .into(binding.imgPoster)
        }

    }

    fun submitList(list: List<Movie>){
        diffUtil.submitList(list)

    }
}