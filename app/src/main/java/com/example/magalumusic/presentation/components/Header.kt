package com.example.magalumusic.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.magalumusic.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String = "",
    avatarResId: Int? = null,
    avatarUri: Uri? = null,
    onAvatarClick: () -> Unit = {},
    leftIconResId: Int? = null,
    onLeftIconClick: (() -> Unit)? = null,
    imageSize: Int
) {
    Spacer(modifier = Modifier.width(15.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (leftIconResId != null && onLeftIconClick != null) {
            Image(
                painter = painterResource(id = leftIconResId),
                contentDescription = "Left Icon",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onLeftIconClick() }
            )
        }

        Spacer(modifier = Modifier.width(28.dp))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f),
        )

        val painter: Painter = when {
            avatarUri != null -> rememberAsyncImagePainter(avatarUri)
            avatarResId != null -> painterResource(id = avatarResId)
            else -> painterResource(id = R.drawable.ic_avatar)
        }

        Image(
            painter = painter,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(imageSize.dp)
                .clip(CircleShape)
                .clickable { onAvatarClick() }
        )
    }
}
