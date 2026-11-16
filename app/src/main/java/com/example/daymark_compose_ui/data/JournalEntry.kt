package com.example.daymark_compose_ui.data

import androidx.annotation.DrawableRes

data class JournalEntry(
    val id: Int,
    val date: String, // ISO date (yyyy-MM-dd)
    val title: String,
    val body: String,
    @param:DrawableRes val imageRes: Int
)
