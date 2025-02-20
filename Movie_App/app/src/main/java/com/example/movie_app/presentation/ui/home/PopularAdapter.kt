package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.moviesPoster.PosterResult
import com.example.movie_app.databinding.ItemMoviesBinding

class PopularAdapter:RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<PosterResult>(){
        override fun areItemsTheSame(oldItem: PosterResult, newItem: PosterResult): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: PosterResult, newItem: PosterResult): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil =AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
       holder.bind(diffUtil.currentList[position])
    }

    inner class PopularViewHolder(private val binding: ItemMoviesBinding):ViewHolder(binding.root){
        fun bind(poster: PosterResult){
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500" +poster.poster_path)
                .into(binding.imgMovie)
        }
    }

    fun submitList(list: List<PosterResult>){
        diffUtil.submitList(list)
    }
}