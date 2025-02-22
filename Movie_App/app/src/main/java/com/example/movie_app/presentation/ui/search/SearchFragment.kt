package com.example.movie_app.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentSearchBinding
import com.example.movie_app.presentation.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchVM: SearchVM by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var allMovieAdapter: AllMovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAllAdapter()
        searchVM.fetchAllMovie()
        setSearchView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setAllAdapter(){
        allMovieAdapter= AllMovieAdapter(){
            val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailedFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rchSearchProduct.adapter = allMovieAdapter
        binding.rchSearchProduct.layoutManager=GridLayoutManager(context,2)
        searchVM.filteredMovies.observe(viewLifecycleOwner){
            allMovieAdapter.submitList(it)
        }
    }

    private fun setSearchView(){
          binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
              override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { searchVM.fetchFilteredMovies(it) }
                  return true
              }

              override fun onQueryTextChange(newText: String?): Boolean {
                  newText?.let { searchVM.fetchFilteredMovies(it) }
                  return true
              }

          })
    }

}