package com.example.daymark_compose_ui.ui.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.SettingsItem
import com.example.daymark_compose_ui.ui.components.StatItem
import com.example.daymark_compose_ui.ui.components.StatsRow

@Composable
fun ProfileScreen(
    settingsOptions: List<SettingsItem>,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item(key = "header") {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.tab_profile),
                    style = MaterialTheme.typography.headlineLarge)
                IconButton(onClick = {}) {
                    Icon(Icons.Outlined.Edit, stringResource(R.string.profile_edit))
                }
            }
        }
        item(key = "personalDetails") {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.profile_avatar),
                    contentDescription = null, modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop)
                Spacer(Modifier.width(14.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Avery Cole", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "A thoughtful builder of better everyday routines.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        item(key = "stats") {
            StatsRow(stats = listOf(StatItem(stringResource(R.string.profile_days_active), "127",
                Icons.Outlined.DateRange),
                StatItem(stringResource(R.string.profile_habit_completion), "76%",
                    Icons.Outlined.CheckCircle),
                StatItem(stringResource(R.string.profile_books_read), "18",
                    Icons.AutoMirrored.Outlined.MenuBook)),
                valueFirst = true
            )
            Spacer(Modifier.height(8.dp))
        }
        item(key = "settings") {
            Text(text = stringResource(R.string.settings),
                style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                settingsOptions.forEach { entry ->
                    SettingsOptions(
                        settingsOptions = entry,
                        darkTheme = darkTheme,
                        onThemeChange = onThemeChange
                    )
                }
            }
        }

    }
}

@Composable
fun SettingsOptions(
    settingsOptions: SettingsItem,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colors.surfaceContainerLowest),
        border = BorderStroke(1.dp, colors.onSurface.copy(alpha = 0.08f))) {
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(settingsOptions.icon, contentDescription = null, modifier = Modifier.size(22.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(settingsOptions.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Text(settingsOptions.subtitle, style = MaterialTheme.typography.bodySmall)
            }
            if (settingsOptions.id == "appearance") {
                AppearanceToggle(darkTheme, onThemeChange)
            } else {
                Icon(
                    Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun AppearanceToggle(darkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    val colors = MaterialTheme.colorScheme
    Surface(shape = CircleShape, color = colors.surface) {
        Row(modifier = Modifier.selectableGroup()) {
            listOf(false, true).forEach { dark ->
                IconToggleButton(
                    checked = dark == darkTheme,
                    onCheckedChange = { onThemeChange(dark) },
                    colors = IconButtonDefaults.iconToggleButtonColors(
                        checkedContainerColor = colors.primary,
                        checkedContentColor = colors.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = if (dark) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                        contentDescription = stringResource(
                            if (dark) R.string.profile_dark_theme else R.string.profile_light_theme
                        ),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
