package com.example.movie_app.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
  private  lateinit var binding: FragmentHomeBinding
    private lateinit var genresAdapter: GenresAdapter
        private lateinit var postersAdapter: PostersAdapter
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upComingAdapter: MovieAdapter
 private val homeVM :HomeVM by viewModels()



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
        homeVM.fetchNowPlaying()
        setPopularAdapter()
        setNowPlayingAdapter()
        setGenresAdapter()
        setPosterAdapter()
        setTopRatedAdapter()
        setUPComingAdapter()

        homeVM.fetchPosters()
        homeVM.fetchGenres()
        homeVM.fetchPopular()
        homeVM.fetchTopRated()
        homeVM.fetchUpComing()


    }


    private fun setGenresAdapter(){
        genresAdapter = GenresAdapter(){selectedPosition ->
            val selectedGenres = homeVM.genres.value?.get(selectedPosition)
            selectedGenres?.let {genre->
                homeVM.nowPlaying.observe(viewLifecycleOwner) {movies->
                    val filteredData = movies.filter {movie->
                        movie.genre_ids.contains(genre.id)
                    }
                    nowPlayingAdapter.submitList(filteredData)
                }
                homeVM.popular.observe(viewLifecycleOwner){movies->
                    val filteredPopular = movies.filter {movie->
                        movie.genre_ids.contains(genre.id)
                    }
                    popularAdapter.submitList(filteredPopular)
                }
                homeVM.topRated.observe(viewLifecycleOwner){movies->
                    val filteredTopRated = movies.filter {movie->
                        movie.genre_ids.contains(genre.id)
                    }
                    topRatedAdapter.submitList(filteredTopRated)
                }
                homeVM.upComing.observe(viewLifecycleOwner){movies->
                    val filteredUpComing= movies.filter {movie->
                        movie.genre_ids.contains(genre.id)
                    }
                    upComingAdapter.submitList(filteredUpComing)
                }

            }
        }
        binding.rcyGenres.adapter=genresAdapter
        binding.rcyGenres.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeVM.genres.observe(viewLifecycleOwner){
            genresAdapter.submitList(it)
        }

    }
    private fun setNowPlayingAdapter(){
        nowPlayingAdapter = MovieAdapter(){ selectedMovie->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailedFragment(selectedMovie.id)
            findNavController().navigate(action)
        }
        binding.rcyNowPlaying.adapter = nowPlayingAdapter
        binding.rcyNowPlaying.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
       homeVM.nowPlaying.observe(viewLifecycleOwner){
           nowPlayingAdapter.submitList(it)
       }
    }
    private fun setPosterAdapter(){
        postersAdapter= PostersAdapter()
        binding.movieViewPager.adapter=postersAdapter
        binding.dotsInteceptor.attachTo(binding.movieViewPager)
        homeVM.posters.observe(viewLifecycleOwner){
            postersAdapter.submitList(it)
        }
    }


    private fun setPopularAdapter(){
        popularAdapter = MovieAdapter(){selectedMovie->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailedFragment(selectedMovie.id)
            findNavController().navigate(action)
        }
        binding.rcyPopular.adapter = popularAdapter
        binding.rcyPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        homeVM.popular.observe(viewLifecycleOwner){
            popularAdapter.submitList(it)
        }
    }

    private fun setTopRatedAdapter(){
        topRatedAdapter = MovieAdapter(){ selectedMovie->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailedFragment(selectedMovie.id)
            findNavController().navigate(action)
        }
        binding.rcyTopRated.adapter = topRatedAdapter
        binding.rcyTopRated.layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeVM.topRated.observe(viewLifecycleOwner){
            topRatedAdapter.submitList(it)
        }
    }

    private fun setUPComingAdapter(){
        upComingAdapter= MovieAdapter(){ selectedMovie->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailedFragment(selectedMovie.id)
            findNavController().navigate(action)
        }
        binding.rcyUpComing.adapter = upComingAdapter
        binding.rcyUpComing.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        homeVM.upComing.observe(viewLifecycleOwner){
            upComingAdapter.submitList(it)
        }
    }



}