package com.example.soundfy.presentation.home

import BottomNavigationBar
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soundfy.presentation.components.ArtistItem
import com.example.soundfy.presentation.components.Header
import com.example.soundfy.ui.navigation.Routes
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val dimens = LocalDimens.current
    val avatarUri by viewModel.avatarUri.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> if (uri != null) viewModel.setAvatarUri(uri) }
    )

    val artists by viewModel.artists.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTopArtists()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(
                title = "Top artistas",
                avatarUri = avatarUri,
                onAvatarClick = {
                    imagePickerLauncher.launch("image/*")
                },
                imageSize = dimens.iconMd.value.toInt(),
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = Routes.HOME,
                avatarUri = avatarUri
            )
        }
    ) { paddingValues ->
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = dimens.spacingBase)
            ) {
                items(artists) { artist ->
                    ArtistItem(artist = artist) {
                        val encodedName = Uri.encode(artist.name)
                        val encodedImage = Uri.encode(artist.picture)
                        navController.navigate("artist/$encodedName/$encodedImage")
                    }
                }
            }
        }
    }
}
