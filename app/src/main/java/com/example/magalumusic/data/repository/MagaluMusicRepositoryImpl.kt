package com.example.magalumusic.data.repository

import com.example.magalumusic.data.remote.deezer.ApiService
import com.example.magalumusic.data.remote.mapper.toDomain
import com.example.magalumusic.domain.model.Album
import com.example.magalumusic.domain.model.Artist
import com.example.magalumusic.domain.repository.MagaluMusicRepository

class MagaluMusicRepositoryImpl(
    private val api: ApiService
) : MagaluMusicRepository {

    override suspend fun getTopArtists(): List<Artist> {
        return try {
            api.getTopArtists().data.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getAlbumsByArtistName(artistName: String): List<Album> {
        return try {
            api.getAlbumsByArtistName(artistName).data.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
