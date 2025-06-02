package com.example.magalumusic.di

import com.example.magalumusic.data.remote.deezer.ApiService
import com.example.magalumusic.data.repository.MagaluMusicRepositoryImpl
import com.example.magalumusic.domain.repository.MagaluMusicRepository
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
    fun provideMagaluMusicRepository(api: ApiService): MagaluMusicRepository =
        MagaluMusicRepositoryImpl(api)
}
