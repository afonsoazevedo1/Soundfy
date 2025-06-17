package com.example.soundfy.di

import com.example.soundfy.data.remote.deezer.ApiService
import com.example.soundfy.data.repository.SoundfyRepositoryImpl
import com.example.soundfy.domain.repository.SoundfyRepository
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
    fun provideSoundfyRepository(api: ApiService): SoundfyRepository =
        SoundfyRepositoryImpl(api)
}
