package com.example.movie_app.presentation.di

import com.example.movie_app.data.auth.AuthRepositoryImpl
import com.example.movie_app.domain.Network.auth.IAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth , firebaseFirestore: FirebaseFirestore):IAuthRepository{
        return AuthRepositoryImpl(firebaseAuth,firebaseFirestore)
    }
}