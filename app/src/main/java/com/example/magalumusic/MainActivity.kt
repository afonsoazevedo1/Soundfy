package com.example.magalumusic

import MagaluMusicNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.magalumusic.ui.theme.MagaluMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagaluMusicTheme {
                MagaluMusicNavHost()
            }
        }
    }
}