package com.example.daymark_compose_ui.data

import androidx.annotation.DrawableRes

data class Place(
    val id: Int,
    val name: String,
    val location: String,
    @param:DrawableRes val imageRes: Int,
    val rating: Double,
    val reviewCount: String
)
