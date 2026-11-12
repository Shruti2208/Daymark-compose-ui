package com.example.daymark_compose_ui.ui.components.habits

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun DateCard(
    day: String,
    date: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) colors.primary else colors.surfaceContainerLowest,
        animationSpec = tween(durationMillis = 180),
        label = "Date card background"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) colors.onPrimary else colors.onSurface,
        animationSpec = tween(durationMillis = 180),
        label = "Date card text"
    )
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = containerColor,
        contentColor = contentColor,
        border = if (isSelected) null else BorderStroke(1.dp, colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
                .selectable(selected = isSelected, role = Role.RadioButton, onClick = onClick)
                .padding(vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(day, style = MaterialTheme.typography.labelSmall)
            Text(date, style = MaterialTheme.typography.labelLarge)
        }
    }
}
