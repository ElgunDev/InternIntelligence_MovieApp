package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.home.moviesPoster.PosterResult
import com.example.movie_app.databinding.ItemPostersBinding

class PostersAdapter:RecyclerView.Adapter<PostersAdapter.PosterViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<PosterResult>(){
        override fun areItemsTheSame(oldItem: PosterResult, newItem: PosterResult): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: PosterResult, newItem: PosterResult): Boolean {
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
        fun bind(poster: PosterResult){
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500" +poster.poster_path)
                .into(binding.imgPoster)
        }

    }

    fun submitList(list: List<PosterResult>){
        diffUtil.submitList(list)

    }
}