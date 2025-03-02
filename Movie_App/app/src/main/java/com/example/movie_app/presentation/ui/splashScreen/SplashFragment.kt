package com.example.movie_app.presentation.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentSplashBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment
(): Fragment() {
    private lateinit var binding:FragmentSplashBinding
    @Inject
     lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val isLogin = checkUserLogin()
        Handler(Looper.getMainLooper()).postDelayed({
//            if (isLogin) {
            findNavController().navigate(R.id.action_splashFragment_to_startFragment)
//            }
//            else {
//                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
//            }
        },3000
        )
    }

    private fun checkUserLogin():Boolean{
        return firebaseAuth.currentUser==null
    }


}