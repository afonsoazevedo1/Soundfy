package com.example.magalumusic.domain.repository

import com.example.magalumusic.domain.model.Album
import com.example.magalumusic.domain.model.Artist

interface MagaluMusicRepository {
    suspend fun getTopArtists(): List<Artist>
    suspend fun getAlbumsByArtistName(artistName: String): List<Album>
}