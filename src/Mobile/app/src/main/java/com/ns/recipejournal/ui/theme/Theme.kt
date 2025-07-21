package com.ns.recipejournal.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppTheme = darkColorScheme(
   surface = Dark,
   onSurface = Yellow,
   onSecondaryContainer = Yellow,
   background = Dark,
   surfaceContainer = Dark
)


@Composable
fun MobileTheme(
   content: @Composable () -> Unit
) {
   MaterialTheme(
      colorScheme = AppTheme,
      typography = Typography,
      content = content
   )
}