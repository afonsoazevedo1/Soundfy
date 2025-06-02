package com.example.magalumusic.data.remote.mapper

import com.example.magalumusic.data.remote.dto.AlbumDto
import com.example.magalumusic.domain.model.Album

fun AlbumDto.toDomain(): Album = Album(
    id = this.id,
    title = this.title,
    coverMedium = this.coverMedium
)
