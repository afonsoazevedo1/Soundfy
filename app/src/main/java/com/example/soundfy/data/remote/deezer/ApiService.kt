package com.example.soundfy.data.remote.deezer

import com.example.soundfy.data.remote.dto.AlbumResponse
import com.example.soundfy.data.remote.dto.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("chart/0/artists")
    suspend fun getTopArtists(): ArtistResponse

    @GET("search/album")
    suspend fun getAlbumsByArtistName(@Query("q") artistName: String): AlbumResponse
}
