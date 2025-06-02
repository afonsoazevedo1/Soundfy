package com.example.magalumusic.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    val id: Int,
    val title: String,
    @SerializedName("cover_medium") val coverMedium: String,
)