package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.home.upComing.UpComing
import com.example.movie_app.databinding.ItemMoviesBinding

class UpComingAdapter(
    private val onClick:(UpComing)->Unit
):RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<UpComing>(){
        override fun areItemsTheSame(oldItem: UpComing, newItem: UpComing): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: UpComing, newItem: UpComing): Boolean {
            return oldItem== newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpComingViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class UpComingViewHolder(private val binding: ItemMoviesBinding):ViewHolder(binding.root){
   fun bind(upComing: UpComing){
       Glide.with(binding.root.context)
           .load("https://image.tmdb.org/t/p/w500" +upComing.poster_path)
           .into(binding.imgMovie)

       binding.root.setOnClickListener(){
           onClick(upComing)
       }
   }
    }

    fun submitList(list: List<UpComing>){
        diffUtil.submitList(list)
    }
}