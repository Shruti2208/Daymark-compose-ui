package com.example.daymark_compose_ui.ui.components

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.daymark_compose_ui.ui.components.explore.ExploreScreen
import com.example.daymark_compose_ui.ui.components.explore.placeDetails.PlaceDetailsScreen
import com.example.daymark_compose_ui.data.SampleData
import com.example.daymark_compose_ui.ui.components.habits.HabitsScreen
import com.example.daymark_compose_ui.ui.components.today.TodayScreen

@Composable
internal fun DaymarkApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val todayRoute = requireNotNull(DaymarkTab.Today.route)
    val habitsRoute = requireNotNull(DaymarkTab.Habits.route)
    val exploreRoute = requireNotNull(DaymarkTab.Explore.route)
    val detailsRoute = "$exploreRoute/place/{placeId}"
    val places = remember { SampleData().samplePlaces }
    var favoriteIds by rememberSaveable { mutableStateOf(intArrayOf()) }
    val toggleFavorite: (Int) -> Unit = { id ->
        favoriteIds = if (id in favoriteIds) {
            favoriteIds.filterNot { it == id }.toIntArray()
        } else {
            favoriteIds + id
        }
    }

    val selectedTab = when (backStackEntry?.destination?.route) {
        habitsRoute -> DaymarkTab.Habits
        exploreRoute -> DaymarkTab.Explore
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
        bottomBar = {
            if (backStackEntry?.destination?.route != detailsRoute) {
                DaymarkNavigationBar(selectedTab, selectTab)
            }
        }
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
            composable(exploreRoute) {
                ExploreScreen(
                    places = places,
                    favoriteIds = favoriteIds,
                    onToggleFavorite = toggleFavorite,
                    onPlaceClick = { id ->
                        navController.navigate("$exploreRoute/place/$id") { launchSingleTop = true }
                    }
                )
            }
            composable(detailsRoute, arguments = listOf(navArgument("placeId") { type = NavType.IntType })) { entry ->
                val placeId = entry.arguments?.getInt("placeId")
                PlaceDetailsScreen(
                    place = places.find { it.id == placeId },
                    isFavorite = placeId != null && placeId in favoriteIds,
                    onToggleFavorite = { placeId?.let(toggleFavorite) },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
