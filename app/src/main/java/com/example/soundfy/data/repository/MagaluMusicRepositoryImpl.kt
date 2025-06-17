package com.example.soundfy.data.repository

import com.example.soundfy.data.remote.deezer.ApiService
import com.example.soundfy.data.remote.mapper.toDomain
import com.example.soundfy.domain.model.Album
import com.example.soundfy.domain.model.Artist
import com.example.soundfy.domain.repository.SoundfyRepository

class SoundfyRepositoryImpl(
    private val api: ApiService
) : SoundfyRepository {

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
