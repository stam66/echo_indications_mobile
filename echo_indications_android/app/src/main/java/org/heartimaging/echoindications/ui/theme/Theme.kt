package org.heartimaging.echoindications.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// We deliberately do NOT use dynamic colour (Material You) — the app's branding
// should be consistent NHS blue across all phones, regardless of wallpaper.

private val LightColorScheme = lightColorScheme(
    primary = NHSBlue,
    onPrimary = Color.White,
    primaryContainer = NHSBlueTint,
    onPrimaryContainer = NHSBlueDark,
    secondary = NHSBlueAccent,
    onSecondary = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = NHSBlueAccent,
    onPrimary = Color.Black,
    primaryContainer = NHSBlueDark,
    onPrimaryContainer = Color.White,
    secondary = NHSBlueAccent,
    onSecondary = Color.Black,
)

@Composable
fun ECHOindicationsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
