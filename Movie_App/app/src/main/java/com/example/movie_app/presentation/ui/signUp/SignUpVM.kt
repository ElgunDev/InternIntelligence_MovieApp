package com.example.movie_app.presentation.ui.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.UseCase.auth.SignUpUseCase
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpVM @Inject constructor(
    private val signUpUseCase: SignUpUseCase
):ViewModel() {
   private val _authState = MutableLiveData<NetworkResource<FirebaseUser?>>()
    val authState: LiveData<NetworkResource<FirebaseUser?>>
        get() = _authState

    fun signUp(name:String,email:String , password:String){
        viewModelScope.launch {
            _authState.value = signUpUseCase.invoke(name, email, password)
        }
    }
}