package com.example.daymark_compose_ui.ui.components.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
internal fun ProgressSummary(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ProgressSummaryRow(Icons.Outlined.CheckCircle, "Habits", "5 / 6", colors.tertiary)
        ProgressSummaryRow(Icons.AutoMirrored.Outlined.MenuBook, "Reading", "24 min", colors.onBackground)
        ProgressSummaryRow(Icons.Outlined.Edit, "Journal", "1 entry", colors.onBackground)
        ProgressSummaryRow(Icons.Outlined.Place, "Places", "1 saved", colors.onBackground)
    }
}

@Composable
private fun ProgressSummaryRow(
    icon: ImageVector,
    label: String,
    value: String,
    iconColor: Color
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = iconColor)
        Text(text = label, modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodySmall)
    }
}
