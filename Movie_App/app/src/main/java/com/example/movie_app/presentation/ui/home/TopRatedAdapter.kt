package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.topRated.TopRated
import com.example.movie_app.databinding.ItemMoviesBinding

class TopRatedAdapter:RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>(){

    private val diffCallBack = object :DiffUtil.ItemCallback<TopRated>(){
        override fun areItemsTheSame(oldItem: TopRated, newItem: TopRated): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: TopRated, newItem: TopRated): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class TopRatedViewHolder(private val binding: ItemMoviesBinding):ViewHolder(binding.root){
        fun bind(topRated: TopRated){
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500" +topRated.poster_path)
                .into(binding.imgMovie)
        }

    }

    fun submitList(list: List<TopRated>){
        diffUtil.submitList(list)
    }

}