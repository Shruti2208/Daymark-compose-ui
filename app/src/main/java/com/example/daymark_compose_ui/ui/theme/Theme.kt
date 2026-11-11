package com.example.daymark_compose_ui.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(primary = Purple80, secondary = PurpleGrey80,
    tertiary = Pink80)

private val LightColorScheme = lightColorScheme(
    primary = DaymarkAccent,
    secondaryContainer = DaymarkSurface,
    onSecondaryContainer = DaymarkText,
    tertiary = DaymarkSuccess,
    onTertiary = DaymarkText,
    background = DaymarkBackground,
    onBackground = DaymarkText,
    surface = DaymarkSurface,
    surfaceContainerLowest = DaymarkCard,
    onSurface = DaymarkText,
    surfaceVariant = DaymarkSurface,
    onSurfaceVariant = DaymarkText
)

@Composable
fun DaymarkcomposeuiTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
