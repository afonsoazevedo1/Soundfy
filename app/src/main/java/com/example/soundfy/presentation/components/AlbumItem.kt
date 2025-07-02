package com.example.soundfy.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.soundfy.domain.model.Album
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun AlbumItem(album: Album) {
    val dimens = LocalDimens.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimens.spacingSm)
    ) {
        AsyncImage(
            model = album.coverMedium,
            contentDescription = null,
            modifier = Modifier
                .size(dimens.albumImageSize)
                .clip(RoundedCornerShape(dimens.cornerMd))
        )
        Spacer(modifier = Modifier.width(dimens.spacingSm))
        Column {
            Text(
                text = album.title,
                color = Color.White,
                fontSize = dimens.textMd
            )
        }
    }
}
