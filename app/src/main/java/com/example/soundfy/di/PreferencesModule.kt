package com.example.soundfy.di

import com.example.soundfy.data.local.SoundfyPersistence
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideSoundfyPersistence(@ApplicationContext context: Context): SoundfyPersistence {
        return SoundfyPersistence(context)
    }
}
