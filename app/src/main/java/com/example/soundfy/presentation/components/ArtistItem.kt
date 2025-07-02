package com.example.soundfy.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.soundfy.domain.model.Artist
import com.example.soundfy.R
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun ArtistItem(artist: Artist, onClick: (() -> Unit)? = null) {
    val context = LocalContext.current
    val dimens = LocalDimens.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
            .padding(dimens.spacingSm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = artist.picture,
            contentDescription = context.getString(R.string.artist_image),
            modifier = Modifier
                .size(dimens.artistImageSize)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(dimens.spacingSm))
        Text(
            text = artist.name,
            color = Color.White,
            fontSize = dimens.textMd
        )
    }
}
