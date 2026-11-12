package com.example.daymark_compose_ui.ui.components

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.daymark_compose_ui.ui.components.habits.HabitsScreen
import com.example.daymark_compose_ui.ui.components.today.TodayScreen

@Composable
internal fun DaymarkApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val todayRoute = requireNotNull(DaymarkTab.Today.route)
    val habitsRoute = requireNotNull(DaymarkTab.Habits.route)
    val selectedTab = when (backStackEntry?.destination?.route) {
        habitsRoute -> DaymarkTab.Habits
        else -> DaymarkTab.Today
    }
    val selectTab: (DaymarkTab) -> Unit = { tab ->
        tab.route?.let { route ->
            if (route != navController.currentDestination?.route) {
                navController.navigate(route) {
                    popUpTo(todayRoute) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { DaymarkNavigationBar(selectedTab, selectTab) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = todayRoute,
            modifier = Modifier.padding(padding).consumeWindowInsets(padding)
        ) {
            composable(todayRoute) {
                TodayScreen(onSeeAllHabits = { selectTab(DaymarkTab.Habits) })
            }
            composable(habitsRoute) {
                HabitsScreen()
            }
        }
    }
}
