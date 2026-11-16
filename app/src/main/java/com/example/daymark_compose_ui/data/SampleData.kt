package com.example.daymark_compose_ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Sync
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
    val sampleJournalEntries = listOf(
        JournalEntry(
            id = 1,
            date = "2026-01-29",
            title = "A slower, calmer day",
            body = "Took a long walk by the water this evening. It felt good to put the phone away and pay attention to the changing light.",
            imageRes = R.drawable.journal_calmer_day
        ),
        JournalEntry(
            id = 2,
            date = "2026-01-26",
            title = "Reading momentum",
            body = "Finished the first half of the book today. Reading a few pages with my morning coffee is starting to feel like a routine.",
            imageRes = R.drawable.journal_reading_momentum
        ),
        JournalEntry(
            id = 3,
            date = "2026-01-23",
            title = "Gratitude",
            body = "Thankful for good food, clear air, and time to focus. The mountain view was a reminder to slow down and look around.",
            imageRes = R.drawable.journal_gratitude
        )
    )
    val sampleSettingsItems = listOf(
        SettingsItem("account", "Account", "Profile, email, and privacy", Icons.Outlined.Person),
        SettingsItem("notifications", "Notifications", "Reminders and daily summary", Icons.Outlined.Notifications),
        SettingsItem("appearance", "Appearance", "Light / Dark theme", Icons.Outlined.Palette),
        SettingsItem("data_sync", "Data & Sync", "Back up and restore your data", Icons.Outlined.Sync),
        SettingsItem("about", "About DAYMARK", "Version 1.0.0", Icons.Outlined.Info)
    )
}
