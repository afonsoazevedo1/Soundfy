package com.example.soundfy.presentation.profile

import BottomNavigationBar
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.soundfy.R
import com.example.soundfy.ui.navigation.Routes
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val dimens = LocalDimens.current

    val avatarUri by viewModel.avatarUri.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> if (uri != null) viewModel.setAvatarUri(uri) }
    )

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = Routes.PROFILE,
                avatarUri = null
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = avatarUri ?: R.drawable.ic_avatar,
                contentDescription = context.getString(R.string.avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimens.avatarSizeLg)
                    .clip(CircleShape)
                    .border(dimens.borderRegular, Color.Black, CircleShape)
                    .clickable {
                        imagePickerLauncher.launch("image/*")
                    }
            )

            Spacer(modifier = Modifier.height(dimens.spacingXs))

            Text(
                text = context.getString(R.string.add_avatar),
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                modifier = Modifier.padding(top = dimens.spacingXs)
            )

            Spacer(modifier = Modifier.height(dimens.spacingMd))

            Text(
                text = context.getString(R.string.name_of_user),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(dimens.spacingXl))

            Button(
                onClick = {
                    viewModel.clearAvatar()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.PROFILE) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(dimens.buttonWidthMd)
                    .height(dimens.buttonHeightMd)
            ) {
                Text(
                    text = context.getString(R.string.exit),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
