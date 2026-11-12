package com.example.daymark_compose_ui.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Habit(
    val name: String,
    val category: String,
    val icon: ImageVector,
    val completedDays: Int,
    val totalDays: Int
)
