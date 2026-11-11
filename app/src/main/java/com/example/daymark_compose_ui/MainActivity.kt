package com.example.daymark_compose_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.daymark_compose_ui.ui.components.TodayScreen
import com.example.daymark_compose_ui.ui.theme.DaymarkcomposeuiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaymarkcomposeuiTheme {
                TodayScreen()
            }
        }
    }
}
