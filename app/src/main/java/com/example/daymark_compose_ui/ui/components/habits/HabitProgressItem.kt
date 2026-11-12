package com.example.daymark_compose_ui.ui.components.habits

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.data.Habit

@Composable
fun HabitProgressItem(
    habit: Habit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val progress = if (habit.totalDays > 0) {
        (habit.completedDays.toFloat() / habit.totalDays).coerceIn(0f, 1f)
    } else {
        0f
    }
    var hasAppeared by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { hasAppeared = true }
    val animatedProgress by animateFloatAsState(
        targetValue = if (hasAppeared) progress else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "Habit progress"
    )
    val chevronDirection = if (LocalLayoutDirection.current == LayoutDirection.Rtl) -1f else 1f

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(colors.tertiary.copy(alpha = 0.18f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(habit.icon, contentDescription = null, tint = colors.tertiary)
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = habit.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "${habit.completedDays} / ${habit.totalDays}",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = habit.category,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.weight(2f).height(6.dp).clip(CircleShape),
                    color = colors.tertiary,
                    trackColor = colors.surface,
                    gapSize = 0.dp,
                    drawStopIndicator = {}
                )
            }
        }
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(18.dp).scale(scaleX = chevronDirection, scaleY = 1f),
            tint = colors.onSurfaceVariant
        )
    }
}
