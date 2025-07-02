package com.example.soundfy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun SoundfyTheme(content: @Composable () -> Unit) {
    val dimens = Dimensions()

    CompositionLocalProvider(
        LocalDimens provides dimens
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}
