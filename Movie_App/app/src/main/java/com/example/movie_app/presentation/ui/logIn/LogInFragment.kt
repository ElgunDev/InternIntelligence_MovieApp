package com.example.movie_app.presentation.ui.logIn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentLogInBinding
import com.example.movie_app.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val loginVM :LoginVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn()
        observe()
        clickSignUp()
    }

    private fun clickSignUp(){
        binding.txtSignUp.setOnClickListener(){
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }

    }

    private fun signIn(){
        binding.btnLogIn.setOnClickListener(){
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            showProgressBar(true)
            loginVM.signIn(email,password)

        }
    }

    private fun observe(){
        loginVM.authState.observe(viewLifecycleOwner){result->
            when(result){
                is NetworkResource.Success->{
                    findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
                   showProgressBar(false)
                }
                is NetworkResource.Error->{
                    Toast.makeText(requireContext(),"SignIn Failed" , Toast.LENGTH_SHORT).show()
                    Log.e("SignInError" ,"SignIn failed: ${result.message}")
                    showProgressBar(false)
                }
                is NetworkResource.Loading->{
                    showProgressBar(true)
                }
            }

        }
    }
    private fun showProgressBar(show:Boolean){
        if (show){
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogIn.text = ""
        }
        else{
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnLogIn.text = "LOG IN "
        }

    }
}