package com.example.movie_app.presentation.ui.logIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.domain.UseCase.auth.SignInUseCase
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val signInUseCase: SignInUseCase

):ViewModel() {
    private val _authState = MutableLiveData<NetworkResource<FirebaseUser?>>()
    val authState: LiveData<NetworkResource<FirebaseUser?>>
        get() = _authState

    fun signIn(email:String , password:String){
        viewModelScope.launch {
            _authState.value = signInUseCase.invoke(email, password)
        }
    }
}