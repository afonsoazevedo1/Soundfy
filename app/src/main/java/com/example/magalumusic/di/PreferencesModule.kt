package com.example.magalumusic.di

import com.example.magalumusic.data.local.MagaluMusicPersistence
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
    fun provideMagaluMusicPersistence(@ApplicationContext context: Context): MagaluMusicPersistence {
        return MagaluMusicPersistence(context)
    }
}
