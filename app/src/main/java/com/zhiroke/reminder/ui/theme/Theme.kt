package com.zhiroke.reminder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

private val LightColorPalette = lightColors(
    primary = PeriwinkleCrayola,
    primaryVariant = MaximumBluePurple,
    onPrimary = DarkGray,
    secondary = UranianBlue,
    secondaryVariant = BabyBlueEyes,
    onSecondary = DarkGray,
    background = White,
    onBackground = Black60,
    surface = GhostWhite,
    onSurface = Black60,
    error = ErrorRed,
    onError = GhostWhite
)

@Composable
fun ReminderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}