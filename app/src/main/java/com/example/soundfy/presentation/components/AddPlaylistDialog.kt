package com.example.soundfy.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.soundfy.R
import com.example.soundfy.ui.theme.LocalDimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaylistDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onSave: (playlistName: String) -> Unit
) {
    val context = LocalContext.current
    val dimens = LocalDimens.current

    if (show) {
        var playlistName by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF262020),
                shape = RoundedCornerShape(dimens.cornerMd)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = dimens.spacingLg, vertical = dimens.spacingLg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = onDismiss) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = context.getString(R.string.close),
                                alignment = Alignment.Center,
                                alpha = 1f
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(dimens.spacingMd))

                    Text(
                        text = context.getString(R.string.name_your_playlist),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = dimens.textSm,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(dimens.spacingLg))

                    TextField(
                        value = playlistName,
                        onValueChange = {
                            playlistName = it
                            if (isError && it.isNotBlank()) {
                                isError = false
                            }
                        },
                        placeholder = {
                            Text(
                                text = context.getString(R.string.my_playlist),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = dimens.textXl,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        isError = isError,
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    if (isError) {
                        Text(
                            text = context.getString(R.string.please_insert_playlist_name),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = dimens.spacingMd, top = dimens.spacingXs)
                        )
                    }

                    Spacer(modifier = Modifier.height(dimens.spacingXxl))

                    Button(
                        onClick = {
                            if (playlistName.isBlank()) {
                                isError = true
                            } else {
                                onSave(playlistName)
                                onDismiss()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                        shape = RoundedCornerShape(dimens.cornerFull),
                        modifier = Modifier
                            .width(dimens.buttonWidthMd)
                            .height(dimens.buttonHeightMd)
                    ) {
                        Text(
                            text = context.getString(R.string.create),
                            fontWeight = FontWeight.Bold,
                            fontSize = dimens.textSm
                        )
                    }
                }
            }
        }
    }
}
