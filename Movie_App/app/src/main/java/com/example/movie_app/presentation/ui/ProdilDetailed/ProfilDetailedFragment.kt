package com.example.movie_app.presentation.ui.ProdilDetailed

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movie_app.databinding.FragmentProfilDetailedBinding
import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilDetailedFragment : Fragment() {
       private lateinit var binding:FragmentProfilDetailedBinding
       private val profileDetailedVM:ProfileDetailedVM by viewModels()
       private lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfilDetailedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserInformation()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let { profileDetailedVM.loadInfo(it) }
        setUserInformation()
        updatePersonalInformation()
        click()
    }

    private fun setUserInformation(){
        profileDetailedVM.user.observe(viewLifecycleOwner){ state->
            when(state){
                is NetworkResource.Success->{
                    val user = state.data
                    user.let {
                        binding.nameInputText.setText(it.name)
                        binding.emailInputText.setText(it.email)
                        binding.finInputText.setText(it.fin)
                        binding.phoneNumberInputText.setText(it.phoneNumber)
                        binding.genderText.setText(it.gender)
                        password=it.password
                    }
                }
                is NetworkResource.Error->{
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResource.Loading->{

                }
            }
        }
        profileDetailedVM.selectedGender.observe(viewLifecycleOwner){
            binding.genderText.setText(it)
        }
    }

    private fun click(){
        binding.imgBackButton.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.genderText.setOnClickListener(){
            showGenderDialog()
        }
    }

    private fun updatePersonalInformation(){
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        binding.btnSave.setOnClickListener(){
            val name = binding.nameInputText.text.toString()
            val email = binding.emailInputText.text.toString()
            val phoneNumber = binding.phoneNumberInputText.text.toString()
            val fin = binding.finInputText.text.toString()
            val updateInfo = userId?.let {
                User(
                    uId = it,
                    name = name,
                    email = email,
                    password =password,
                    profilePhoto = null,
                    fin = fin,
                    phoneNumber = phoneNumber,
                    gender = profileDetailedVM.selectedGender.value
                    )
            }
            if (updateInfo != null) {
                profileDetailedVM.updateInfo(updateInfo)
            }
            findNavController().popBackStack()
        }
    }

    private fun showGenderDialog(){
        val genderOption = arrayOf("Kişi", "Qadın" , "Qeyd Etmək istəmirəm")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Cinsi seçin")
            .setSingleChoiceItems(genderOption,-1){dialog , which->
                val selectedGender = genderOption[which]
                 profileDetailedVM.updateGender(selectedGender)
            }
            .setPositiveButton("Seç"){dialog ,_->
                dialog.dismiss()
            }
            .setNegativeButton("Imtina"){dialog, _->
                dialog.dismiss()

            }
        builder.create().show()
    }


}