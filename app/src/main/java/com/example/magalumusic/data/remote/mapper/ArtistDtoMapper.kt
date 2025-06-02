package com.example.magalumusic.data.remote.mapper

import com.example.magalumusic.data.remote.dto.ArtistDto
import com.example.magalumusic.domain.model.Artist

fun ArtistDto.toDomain(): Artist = Artist(
    id = this.id,
    name = this.name,
    picture = this.picture_medium
)