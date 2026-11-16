package com.example.daymark_compose_ui.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

internal data class StatItem(val label: String, val value: String, val icon: ImageVector)

@Composable
internal fun StatsRow(
    stats: List<StatItem>,
    modifier: Modifier = Modifier,
    valueFirst: Boolean = false
) {
    if (stats.isEmpty()) return

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp).height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            stats.forEachIndexed { index, (label, value, icon) ->
                if (index > 0) {
                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight(),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.14f)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
                    val labelStyle = MaterialTheme.typography.labelSmall
                    val valueStyle = MaterialTheme.typography.titleSmall
                    if (valueFirst) {
                        Text(value, style = valueStyle, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text(label, style = labelStyle, textAlign = TextAlign.Center)
                    } else {
                        Text(label, style = labelStyle, textAlign = TextAlign.Center)
                        Text(value, style = valueStyle, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
