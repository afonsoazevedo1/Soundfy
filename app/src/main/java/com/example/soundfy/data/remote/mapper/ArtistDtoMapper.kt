package com.example.soundfy.data.remote.mapper

import com.example.soundfy.data.remote.dto.ArtistDto
import com.example.soundfy.domain.model.Artist

fun ArtistDto.toDomain(): Artist = Artist(
    id = this.id,
    name = this.name,
    picture = this.picture_medium
)