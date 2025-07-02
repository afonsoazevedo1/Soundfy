package com.example.soundfy.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(

    // Spacing
    val spacingXs: Dp = 4.dp,
    val spacingSm: Dp = 8.dp,
    val spacingMd: Dp = 12.dp,
    val spacingBase: Dp = 16.dp,
    val spacingLg: Dp = 24.dp,
    val spacingXl: Dp = 32.dp,
    val spacingXxl: Dp = 48.dp,

    // Text Sizes
    val textXs: TextUnit = 12.sp,
    val textSm: TextUnit = 14.sp,
    val textMd: TextUnit = 16.sp,
    val textLg: TextUnit = 20.sp,
    val textXl: TextUnit = 24.sp,
    val textXxl: TextUnit = 32.sp,

    // Icon Sizes
    val iconXs: Dp = 16.dp,
    val iconSm: Dp = 20.dp,
    val iconMd: Dp = 24.dp,
    val iconLg: Dp = 32.dp,
    val iconXl: Dp = 40.dp,
    val iconXxl: Dp = 48.dp,

    // Avatar Sizes
    val avatarSizeSm: Dp = 40.dp,
    val avatarSizeMd: Dp = 48.dp,
    val avatarSizeLg: Dp = 62.dp,
    val avatarSizeXl: Dp = 72.dp,

    // Button Sizes
    val buttonHeightSm: Dp = 36.dp,
    val buttonHeightMd: Dp = 48.dp,
    val buttonHeightLg: Dp = 56.dp,
    val buttonWidthMd: Dp = 150.dp,
    val buttonWidthXl: Dp = 185.dp,

    // Album/Card Image Sizes
    val albumImageSize: Dp = 70.dp,

    // Artist Image Size
    val artistImageSize: Dp = 60.dp,

    // Corner Radius
    val cornerSm: Dp = 4.dp,
    val cornerMd: Dp = 8.dp,
    val cornerLg: Dp = 16.dp,
    val cornerFull: Dp = 50.dp,

    // Elevation
    val elevationLow: Dp = 2.dp,
    val elevationMid: Dp = 4.dp,
    val elevationHigh: Dp = 8.dp,

    // Divider/Border
    val borderThin: Dp = 1.dp,
    val borderRegular: Dp = 2.dp
)

val LocalDimens = staticCompositionLocalOf { Dimensions() }