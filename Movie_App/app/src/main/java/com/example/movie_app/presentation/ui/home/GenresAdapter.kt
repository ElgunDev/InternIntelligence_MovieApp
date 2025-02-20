package com.example.movie_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movie_app.R
import com.example.movie_app.data.network.models.genres.Genre
import com.example.movie_app.databinding.ItemGenresBinding

class GenresAdapter(
    private val onItemClick:(Int)->Unit
            ):RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Genre>(){
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
          return oldItem==newItem
        }


    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    private var selectedPosition=0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GenresViewHolder(binding)

    }

    override fun getItemCount(): Int {
       return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre = diffUtil.currentList[position]
        holder.bind(genre , position)
        holder.itemView.setOnClickListener(){
            val oldPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
            onItemClick(position)
        }

    }

    inner class GenresViewHolder(private val binding:ItemGenresBinding):ViewHolder(binding.root){
        fun bind(genre: Genre, position: Int){
              binding.txtGenres.text = genre.name
            if (position==selectedPosition){
                binding.txtGenres.setBackgroundResource(R.drawable.selected_category_bg)
            }
            else{
                binding.txtGenres.setBackgroundResource(R.drawable.unselected_category_bg)
            }


        }
    }
    fun submitList(list:List<Genre>){
        diffUtil.submitList(list)
    }
}