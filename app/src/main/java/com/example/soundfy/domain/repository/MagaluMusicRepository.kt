package com.example.soundfy.domain.repository

import com.example.soundfy.domain.model.Album
import com.example.soundfy.domain.model.Artist

interface SoundfyRepository {
    suspend fun getTopArtists(): List<Artist>
    suspend fun getAlbumsByArtistName(artistName: String): List<Album>
}