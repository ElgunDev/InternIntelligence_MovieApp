package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.nowPlaying.NowPlaying
import com.example.movie_app.databinding.ItemMoviesBinding

class NowPlayingAdapter:RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>( ){

    private val diffCallBack = object :DiffUtil.ItemCallback<NowPlaying>(){
        override fun areItemsTheSame(oldItem: NowPlaying, newItem: NowPlaying): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: NowPlaying, newItem: NowPlaying): Boolean {
            return oldItem==newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowPlayingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class NowPlayingViewHolder(private val binding:ItemMoviesBinding):ViewHolder(binding.root){
         fun bind(nowPlaying: NowPlaying){
             Glide.with(binding.root.context)
                 .load("https://image.tmdb.org/t/p/w500" +nowPlaying.poster_path)
                 .into(binding.imgMovie)
         }
    }

    fun submitList( list: List<NowPlaying>){
        diffUtil.submitList(list)
    }
}