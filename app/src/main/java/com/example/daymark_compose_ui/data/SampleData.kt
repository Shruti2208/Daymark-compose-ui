package com.example.daymark_compose_ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.WaterDrop

class SampleData {

    public val sampleWeek = listOf(
        "Mon" to "26",
        "Tue" to "27",
        "Wed" to "28",
        "Thu" to "29",
        "Fri" to "30",
        "Sat" to "31",
        "Sun" to "1"
    )

    val filters = listOf(
        "All",
        "Health",
        "Mind",
        "Productivity"
    )
    val habits = listOf(
        Habit("Move for 30 min", "Health", Icons.AutoMirrored.Outlined.DirectionsRun, 5, 7),
        Habit("Read", "Mind", Icons.AutoMirrored.Outlined.MenuBook, 6, 7),
        Habit("Drink water", "Health", Icons.Outlined.WaterDrop, 4, 7),
        Habit("Meditate", "Mind", Icons.Outlined.SelfImprovement, 3, 7),
        Habit("Deep work", "Productivity", Icons.Outlined.Laptop, 5, 7)
    )
}
