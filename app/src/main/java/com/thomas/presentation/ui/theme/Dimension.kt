package com.thomas.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimension(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val normal: Dp = 16.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 24.dp,
    val iconSmall: Dp = 20.dp,
    val iconMedium: Dp = 24.dp,
    val iconLarge: Dp = 40.dp,
    val iconExtraLarge: Dp = 80.dp,
    val elevationSmall: Dp = 2.dp
)

val LocalDimension = compositionLocalOf { Dimension() }

val MaterialTheme.dimen: Dimension
    @Composable
    @ReadOnlyComposable
    get() = LocalDimension.current