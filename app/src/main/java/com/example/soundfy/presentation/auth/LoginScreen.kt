package com.example.soundfy.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.soundfy.R
import com.example.soundfy.ui.theme.LocalDimens

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {

    val context = LocalContext.current
    val dimens = LocalDimens.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = context.getString(R.string.login_with_spotify),
                color = Color.White,
                fontSize = dimens.textMd
            )
            Spacer(modifier = Modifier.height(dimens.spacingXl))
            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DB954)),
                shape = RoundedCornerShape(dimens.cornerFull),
                modifier = Modifier
                    .width(dimens.buttonWidthMd)
                    .height(dimens.buttonHeightMd)
            ) {
                Text(
                    text = context.getString(R.string.enter),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimens.textSm
                )
            }
        }
    }
}
