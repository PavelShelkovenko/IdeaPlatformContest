package com.pavelshelkovenko.ideaplatformcontest.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val AppColorScheme = lightColorScheme(
    primary = LightBlue,
    onPrimary = White,
    onSecondary = Color.Black,
    tertiary = Blue,
    onTertiary = Color.Gray,
)

@Composable
fun IdeaPlatformContestTheme(
    content: @Composable () -> Unit
) {
    SystemBarColors()
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}
@Composable
fun SystemBarColors() {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setSystemBarsColor(
            color = LightBlue
        )
        setNavigationBarColor(
            color = LightBlue
        )
    }
}