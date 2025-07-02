package com.example.soundfy.presentation.artists

import BottomNavigationBar
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soundfy.R
import com.example.soundfy.presentation.components.AlbumItem
import com.example.soundfy.presentation.components.Header
import com.example.soundfy.ui.navigation.Routes
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun ArtistScreen(
    artistName: String,
    avatarUri: Uri?,
    navController: NavController,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    val albums by viewModel.albums.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current
    val dimens = LocalDimens.current

    LaunchedEffect(artistName) {
        viewModel.fetchAlbumsByName(artistName)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(
                title = artistName.ifBlank { "" },
                avatarUri = avatarUri,
                leftIconResId = R.drawable.ic_arrow_left,
                onLeftIconClick = { navController.popBackStack() },
                imageSize = dimens.avatarSizeLg.value.toInt(),
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = Routes.ARTIST,
                avatarUri = avatarUri
            )
        }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            albums.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = context.getString(R.string.no_album),
                        color = Color.White
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = dimens.spacingMd)
                        .padding(paddingValues)
                ) {
                    items(albums) { album ->
                        AlbumItem(album)
                    }
                }
            }
        }
    }
}
