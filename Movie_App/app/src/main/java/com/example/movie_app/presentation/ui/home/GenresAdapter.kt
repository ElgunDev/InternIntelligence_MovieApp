package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movie_app.data.network.models.Genre
import com.example.movie_app.databinding.ItemGenresBinding

class GenresAdapter:RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Genre>(){
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return newItem==oldItem
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
           return newItem==oldItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenresViewHolder(binding)

    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class GenresViewHolder(private val binding:ItemGenresBinding):ViewHolder(binding.root){
        fun bind(genre: Genre){
              binding.txtGenres.text = genre.name
        }
    }
    fun submitList(list:List<Genre>){
        diffUtil.submitList(list)
    }
}