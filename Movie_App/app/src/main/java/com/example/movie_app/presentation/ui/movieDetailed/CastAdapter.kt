package com.example.movie_app.presentation.ui.movieDetailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movie_app.data.network.models.detailed.Cast
import com.example.movie_app.databinding.ItemCastsBinding

class CastAdapter:RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private val diffCallBack = object :DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding =ItemCastsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(diffUtil.currentList[position])
    }

    inner class CastViewHolder(private val binding:ItemCastsBinding):ViewHolder(binding.root){
          fun bind(cast: Cast){
              Glide.with(binding.root.context)
                  .load("https://image.tmdb.org/t/p/w500" +cast.profile_path)
                  .into(binding.imgMovie)
              binding.txtCastName.text = cast.name
          }
    }
    fun submitList(list: List<Cast>){
        diffUtil.submitList(list)
    }
}