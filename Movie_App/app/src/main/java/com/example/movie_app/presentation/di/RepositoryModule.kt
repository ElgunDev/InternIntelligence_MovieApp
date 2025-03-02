package com.example.movie_app.presentation.di

import com.example.movie_app.data.network.repository.auth.AuthRepositoryImpl
import com.example.movie_app.data.network.repository.favorite.FavoriteImplRepository
import com.example.movie_app.data.network.repository.profilDetail.ProfileInfoImplRepository
import com.example.movie_app.domain.Network.repository.auth.IAuthRepository
import com.example.movie_app.domain.Network.repository.favorite.IFavoriteRepository
import com.example.movie_app.domain.Network.repository.profilDetail.IProfileInfoRepository
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
    fun provideAuthRepository(firebaseAuth: FirebaseAuth , firebaseFirestore: FirebaseFirestore): IAuthRepository {
        return AuthRepositoryImpl(firebaseAuth,firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(firebaseFirestore: FirebaseFirestore,firebaseAuth: FirebaseAuth):IFavoriteRepository{
        return FavoriteImplRepository(firebaseFirestore,firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideProfileInfoRepository(fireStore: FirebaseFirestore):IProfileInfoRepository{
        return ProfileInfoImplRepository(fireStore)

    }
}