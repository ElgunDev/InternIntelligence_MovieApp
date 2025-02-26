package com.example.movie_app.presentation.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding:FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteVM:FavoriteVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        favoriteVM.fetchFavoriteMovie()
        setFavoriteAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

   private fun setFavoriteAdapter(){
        favoriteAdapter = FavoriteAdapter({
            favoriteVM.deleteMovie(it)
            favoriteVM.fetchFavoriteMovie()
        }, { selectedMovie ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToMovieDetailedFragment(selectedMovie.id)
            findNavController().navigate(action)
        }
            )
        binding.rcyFav.adapter = favoriteAdapter
        binding.rcyFav.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        favoriteVM.favoriteMovie.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.layoutMovie.visibility=View.GONE
                binding.layoutNoMovie.visibility = View.VISIBLE
            }
            else {
                favoriteAdapter.submitList(it)
                binding.layoutMovie.visibility=View.VISIBLE
                binding.layoutNoMovie.visibility = View.GONE
            }
        }
    }


}