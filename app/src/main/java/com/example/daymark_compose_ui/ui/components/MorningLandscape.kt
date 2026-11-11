package com.example.daymark_compose_ui.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
internal fun MorningLandscape(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Canvas(modifier) {
        val width = size.width
        val height = size.height
        drawCircle(
            color = Color(0xFFFFBB6C),
            radius = size.minDimension * 0.11f,
            center = Offset(width * 0.73f, height * 0.24f)
        )

        val distantHills = Path().apply {
            moveTo(0f, height)
            cubicTo(width * 0.20f, height * 0.91f, width * 0.29f, height * 0.63f, width * 0.43f, height * 0.49f)
            cubicTo(width * 0.58f, height * 0.32f, width * 0.64f, height * 0.66f, width * 0.78f, height * 0.64f)
            quadraticTo(width * 0.89f, height * 0.64f, width, height * 0.48f)
            lineTo(width, height)
            close()
        }
        drawPath(
            path = distantHills,
            brush = Brush.horizontalGradient(listOf(colors.surface.copy(alpha = 0f), colors.surface, colors.tertiary.copy(alpha = 0.38f)))
        )

        val nearHills = Path().apply {
            moveTo(width * 0.28f, height)
            cubicTo(width * 0.51f, height * 0.93f, width * 0.53f, height * 0.71f, width * 0.68f, height * 0.76f)
            cubicTo(width * 0.81f, height * 0.80f, width * 0.89f, height * 0.46f, width, height * 0.43f)
            lineTo(width, height)
            close()
        }
        drawPath(
            path = nearHills,
            brush = Brush.horizontalGradient(listOf(colors.tertiary.copy(alpha = 0f), colors.tertiary))
        )
    }
}
