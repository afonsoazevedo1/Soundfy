package com.example.magalumusic.presentation.playlist

import BottomNavigationBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.magalumusic.presentation.components.AddPlaylistDialog
import com.example.magalumusic.presentation.components.Header
import com.example.magalumusic.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    navController: NavController,
    viewModel: PlaylistViewModel = hiltViewModel()
) {
    val avatarUri = viewModel.avatarUri
    val playlists by viewModel.playlists.collectAsState()

    var showAlert by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Header(
                title = "Minhas playlists",
                avatarUri = avatarUri,
                imageSize = 32
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = Routes.PLAYLISTS,
                avatarUri = avatarUri
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (playlists.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhuma playlist criada.",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 72.dp)
                ) {
                    items(playlists) { playlist ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(playlist.imageUrl),
                                contentDescription = playlist.name,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 12.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column {
                                Text(
                                    text = playlist.name,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = playlist.author,
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }

            Button(
                onClick = { showAlert = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .height(42.dp)
                    .width(185.dp)
            ) {
                Text(text = "Criar playlist", fontWeight = FontWeight.Bold)
            }
        }

        AddPlaylistDialog(
            show = showAlert,
            onDismiss = { showAlert = false },
            onSave = { name ->
                viewModel.addPlaylist(name)
                showAlert = false
            }
        )
    }
}
