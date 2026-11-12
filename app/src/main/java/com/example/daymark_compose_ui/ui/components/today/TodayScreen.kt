package com.example.daymark_compose_ui.ui.components.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.ui.theme.DaymarkcomposeuiTheme

@Composable
fun TodayScreen(onSeeAllHabits: () -> Unit, modifier: Modifier = Modifier) {
    var walkCompleted by rememberSaveable { mutableStateOf(true) }
    var readingCompleted by rememberSaveable { mutableStateOf(true) }
    var waterCompleted by rememberSaveable { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp)) { // 1. Header
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text("D A Y M A R K", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.notifications))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(min = 112.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("Good morning,\nShruti", style = MaterialTheme.typography.headlineLarge)
                Text("Thu, Jan 29, 2026", style = MaterialTheme.typography.bodyMedium)
            }
            MorningLandscape(modifier = Modifier.weight(2f).height(104.dp))
        }
        Spacer(modifier = Modifier.height(28.dp))

        // 3. Progress overview
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(140.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(progress = { 0.73f },
                    modifier = Modifier.fillMaxSize(), strokeWidth = 10.dp,
                    color = colors.primary, trackColor = colors.surface, gapSize = 0.dp)
                Column(verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "73%", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(
                        text = "Daily Progress",
                        style = MaterialTheme.typography.bodySmall,

                        )
                    Text(text = "8 of 11", style = MaterialTheme.typography.bodySmall)
                }

            }
            Spacer(modifier = Modifier.width(24.dp))
            ProgressSummary(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(24.dp))

        // 4. Today's habits
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Today’s Habits", style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = onSeeAllHabits) {
                Text("See all →")
            }
        }
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HabitCard(
                title = "Move for 30 min",
                subtitle = "Workout / Walk",
                icon = Icons.AutoMirrored.Outlined.DirectionsRun,
                iconTint = colors.tertiary,
                isCompleted = walkCompleted,
                onCheckedChange = { walkCompleted = it }
            )
            HabitCard(
                title = "Read",
                subtitle = "≥ 20 min",
                icon = Icons.AutoMirrored.Outlined.MenuBook,
                iconTint = colors.primary,
                isCompleted = readingCompleted,
                onCheckedChange = { readingCompleted = it }
            )
            HabitCard(
                title = "Drink water",
                subtitle = "8 glasses",
                icon = Icons.Outlined.WaterDrop,
                iconTint = colors.tertiary,
                isCompleted = waterCompleted,
                onCheckedChange = { waterCompleted = it }
            )
        }
    }
}

@Preview
@Composable
private fun TodayScreenPreview() {
    DaymarkcomposeuiTheme {
        TodayScreen(onSeeAllHabits = {})
    }
}
