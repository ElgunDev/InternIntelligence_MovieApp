package com.example.movie_app.presentation.ui.signUp

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movie_app.databinding.FragmentSignUpBinding
import com.example.movie_app.presentation.utils.NetworkResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        click()
        observe()
        signUp()
    }

    private fun click(){
        binding.btnBack.setOnClickListener(){
            findNavController().popBackStack()
        }


    }

    private fun signUp(){
        binding.btnSignUp.setOnClickListener {
            val name = binding.edtFullName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val profileImage = ""


            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Bütün sahələri doldurun!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password.length < 6) {
                Toast.makeText(requireContext(), "Şifrə ən azı 6 simvol olmalıdır!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showProgressBar(true)

            signUpViewModel.signUp(name, email, password ,profileImage)

        }
    }

    private fun observe(){
        signUpViewModel.authState.observe(viewLifecycleOwner){result->
            when(result){
                is NetworkResource.Success->{
                    findNavController().popBackStack()
                    showProgressBar(false)
                }
                is NetworkResource.Error->{
                    Log.e("SignUpError", "SignUp Failed: ${result.message}")
                    Toast.makeText(requireContext(),"SignUP Failed ${result.message}" , Toast.LENGTH_SHORT).show()
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
            binding.btnSignUp.text = ""
        }
        else{
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnSignUp.text = "SIGN UP"
        }

    }
}