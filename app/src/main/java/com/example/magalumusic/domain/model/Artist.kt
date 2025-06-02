package com.example.magalumusic.domain.model

import com.google.gson.annotations.SerializedName

data class Artist(
    val id: Long,
    val name: String,
    @SerializedName("picture_medium") val picture: String
)
