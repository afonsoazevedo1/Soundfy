package com.example.soundfy.presentation.components

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.soundfy.R
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String = "",
    avatarResId: Int? = null,
    avatarUri: Uri? = null,
    onAvatarClick: () -> Unit = {},
    leftIconResId: Int? = null,
    onLeftIconClick: (() -> Unit)? = null,
    imageSize: Int // mantém Int porque o consumo já usa `.value.toInt()`
) {
    val context = LocalContext.current
    val dimens = LocalDimens.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimens.spacingBase,
                vertical = dimens.spacingSm
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (leftIconResId != null && onLeftIconClick != null) {
            Image(
                painter = painterResource(id = leftIconResId),
                contentDescription = context.getString(R.string.left_icon),
                modifier = Modifier
                    .size(dimens.iconMd)
                    .clickable { onLeftIconClick() }
            )
        }

        Spacer(modifier = Modifier.width(dimens.spacingMd))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(1f)
        )

        val painter: Painter = when {
            avatarUri != null -> rememberAsyncImagePainter(avatarUri)
            avatarResId != null -> painterResource(id = avatarResId)
            else -> painterResource(id = R.drawable.ic_avatar)
        }

        Image(
            painter = painter,
            contentDescription = context.getString(R.string.avatar),
            modifier = Modifier
                .size(imageSize.dp)
                .clip(CircleShape)
                .clickable { onAvatarClick() }
        )
    }
}
