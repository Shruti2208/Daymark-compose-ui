package com.example.daymark_compose_ui.data

import androidx.annotation.DrawableRes

data class Place(
    val id: Int,
    val name: String,
    val location: String,
    @param:DrawableRes val imageRes: Int,
    val rating: Double,
    val reviewCount: String,
    val description: String? = null,
    val tags: List<String> = emptyList(),
    val elevation: String? = null,
    val trailLength: String? = null,
    val estimatedTime: String? = null
)
