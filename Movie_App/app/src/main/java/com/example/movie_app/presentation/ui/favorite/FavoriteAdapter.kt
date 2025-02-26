package com.example.movie_app.presentation.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val onDeleteClick:(Int)->Unit,
    private val onItemClick:(MovieDetail)->Unit
):RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    val diffCallBack = object :DiffUtil.ItemCallback<MovieDetail>(){
        override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class FavoriteViewHolder(private val binding:ItemFavoriteBinding):ViewHolder(binding.root){
       fun bind(movie:MovieDetail){
           Glide.with(binding.root.context)
               .load("https://image.tmdb.org/t/p/w500" +movie.poster_path)
               .into(binding.imgMovie)
           binding.txtTittle.text = movie.title
           binding.txtRunTime.text = movie.runtime.toString()
           binding.txtReleaseDate.text = movie.release_date
           binding.txtVoteAverage.text = movie.vote_average.toString()
           binding.btnDelete.setOnClickListener(){
               onDeleteClick(movie.id)
           }
           binding.root.setOnClickListener(){
               onItemClick(movie)
           }
       }
    }

    fun submitList(list: List<MovieDetail>){
        diffUtil.submitList(list)
    }
}