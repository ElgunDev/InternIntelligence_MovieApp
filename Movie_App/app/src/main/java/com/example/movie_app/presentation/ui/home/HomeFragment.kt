package com.example.movie_app.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
  private  lateinit var binding: FragmentHomeBinding
    private lateinit var genresAdapter: GenresAdapter
  private val homeVM:HomeVM by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setGenresAdapter(){
        genresAdapter = GenresAdapter()

    }




}