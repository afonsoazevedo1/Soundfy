package com.example.magalumusic.presentation.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.magalumusic.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaylistDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onSave: (playlistName: String) -> Unit
) {
    if (show) {
        var playlistName by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF262020),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = onDismiss) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "Fechar",
                                alignment = Alignment.Center,
                                alpha = 1f
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Dê um nome a sua playlist",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(24.dp))

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
                                text = "Minha playlist #1",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
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
                            text = "Por favor, preencha o nome da playlist",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

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
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .width(150.dp)
                            .height(48.dp)
                    ) {
                        Text("Criar", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
