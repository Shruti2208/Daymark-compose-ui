package com.example.daymark_compose_ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.WaterDrop
import com.example.daymark_compose_ui.R

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
    val samplePlaces = listOf(
        Place(
            id = 1,
            name = "Lake Crescent",
            location = "Olympic National Park",
            imageRes = R.drawable.place_lake_crescent,
            rating = 4.8,
            reviewCount = "1.2k",
            description = "A clear glacial lake framed by forest and mountains. Walk along the shoreline, stop for a picnic, or take in the view from the water.",
            tags = listOf("Olympic National Park", "Hiking", "Nature"),
            elevation = "573 ft",
            trailLength = "2.4 mi",
            estimatedTime = "1–2 hr"
        ),
        Place(2, "Cape Kiwanda", "Oregon Coast", R.drawable.place_cape_kiwanda, 4.7, "892"),
        Place(3, "Twin Falls", "Snoqualmie, WA", R.drawable.place_twin_falls, 4.6, "640"),
        Place(4, "Mount Rainier", "Pierce County, WA", R.drawable.place_mount_rainier, 4.9, "2.3k")
    )
}
