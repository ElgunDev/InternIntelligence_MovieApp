package com.example.movie_app.presentation.ui.movieDetailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movie_app.R
import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.databinding.FragmentMovieDetailedBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailedFragment : Fragment() {
    private lateinit var binding:FragmentMovieDetailedBinding
    private lateinit var castAdapter: CastAdapter
    private lateinit var youTubePlayerView :YouTubePlayerView
    private val movieDetailedVM:MovieDetailedVM by viewModels()
    private val args:MovieDetailedFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId =args.movieId
        youTubePlayerView = binding.youtubePlayer
        movieDetailedVM.fetchMovieDetailed(movieId)
        movieDetailedVM.fetchCast(movieId)
        movieDetailedVM.loadMovieTrailer(movieId)
        setCastAdapter()
        loadYoutubePlayer()
        observe()

        super.onViewCreated(view, savedInstanceState)
    }


    private fun observe(){
        movieDetailedVM.movieDetailed.observe(viewLifecycleOwner){
             bindingMovieDetailed(it)
            backButton(it)
            binding.btnDownload.setOnClickListener(){
                movieDetailedVM.movieDetailed.value?.let {movie->
                    movieDetailedVM.saveToFavorites(movie)
                }
            }
        }
    }


    private fun bindingMovieDetailed(movieDetail: MovieDetail){
        binding.apply {
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500" +movieDetail.poster_path)
                .into(binding.imgMoviePoster)

            txtMovieTitle.text = movieDetail.title
            txtRating.text= movieDetail.vote_average.toString()
            txtReleaseDate.text = movieDetail.release_date
            txtRuntime.text = movieDetail.runtime.toString()
          txtGenres.text = movieDetail.genres.joinToString(", ") { it.name }
            txtOverview.text = movieDetail.overview
            collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(requireContext(), R.color.light_main_color))
            collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(requireContext(), R.color.light_main_color))

        }
    }

    private fun setCastAdapter(){
        castAdapter = CastAdapter()
        binding.rcyCast.adapter = castAdapter
        binding.rcyCast.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        movieDetailedVM.cast.observe(viewLifecycleOwner){
            castAdapter.submitList(it)
        }

    }

    private fun loadYoutubePlayer(){
            movieDetailedVM.trailerKey.observe(viewLifecycleOwner) {
                youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(it, 0f)
                        super.onReady(youTubePlayer)
                    }
                })
            }
    }
    private fun backButton(movieDetail:MovieDetail){
        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = movieDetail.title
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}