package com.example.movie_app.presentation.ui.ProdilDetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.UseCase.profile.ProfileUseCase
import com.example.movie_app.domain.UseCase.profile.UpdateUseCase
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailedVM @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val updateUseCase: UpdateUseCase
):ViewModel() {

    private val _user = MutableLiveData<NetworkResource<User>>()
    val user :LiveData<NetworkResource<User>>
        get() = _user

    private val _selectedGender = MutableLiveData<String>()
    val selectedGender :LiveData<String>
        get() = _selectedGender

    fun updateGender(newGender:String){
        _selectedGender.value = newGender
    }
    fun loadInfo(uId:String){
        _user.value = NetworkResource.Loading()
        viewModelScope.launch {
            _user.value = profileUseCase.invoke(uId)
        }
    }
    fun updateInfo(user: User){
        viewModelScope.launch {
            val uId = FirebaseAuth.getInstance().currentUser?.uid
            _user.value = uId?.let { updateUseCase.invoke(it,user) }

        }
    }
}