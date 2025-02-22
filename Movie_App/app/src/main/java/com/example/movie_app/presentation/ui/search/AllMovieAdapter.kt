package com.example.movie_app.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.search.AllMovie
import com.example.movie_app.databinding.ItemMoviesBinding

class AllMovieAdapter(
    private val onClick: (AllMovie)->Unit
):RecyclerView.Adapter<AllMovieAdapter.AllMovieViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<AllMovie>(){
        override fun areItemsTheSame(oldItem: AllMovie, newItem: AllMovie): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: AllMovie, newItem: AllMovie): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AllMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: AllMovieViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class AllMovieViewHolder(private val  binding: ItemMoviesBinding):ViewHolder(binding.root){
         fun bind(allMovie: AllMovie){
             Glide.with(binding.root.context)
                 .load("https://image.tmdb.org/t/p/w500" +allMovie.poster_path)
                 .into(binding.imgMovie)

             binding.root.setOnClickListener(){
                 onClick(allMovie)
             }
         }
    }
    fun submitList(list: List<AllMovie>){
        diffUtil.submitList(list)
    }
}