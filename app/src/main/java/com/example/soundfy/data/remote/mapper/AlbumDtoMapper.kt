package com.example.soundfy.data.remote.mapper

import com.example.soundfy.data.remote.dto.AlbumDto
import com.example.soundfy.domain.model.Album

fun AlbumDto.toDomain(): Album = Album(
    id = this.id,
    title = this.title,
    coverMedium = this.coverMedium
)
