package com.example.daymark_compose_ui.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R

internal enum class DaymarkTab(
    @param:StringRes val label: Int,
    val outlinedIcon: ImageVector,
    val filledIcon: ImageVector,
    val route: String? = null
) {
    Today(R.string.tab_today, Icons.Outlined.Home, Icons.Filled.Home, "today"),
    Habits(R.string.tab_habits, Icons.Outlined.CheckCircle, Icons.Filled.CheckCircle, "habits"),
    Explore(R.string.tab_explore, Icons.Outlined.Place, Icons.Filled.Place,"explore"),
    Journal(
        R.string.tab_journal,
        Icons.AutoMirrored.Outlined.MenuBook,
        Icons.AutoMirrored.Filled.MenuBook,"journal"
    ),
    Profile(R.string.tab_profile, Icons.Outlined.Person, Icons.Filled.Person,"profile")
}

@Composable
internal fun DaymarkNavigationBar(
    selectedTab: DaymarkTab,
    onTabSelected: (DaymarkTab) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val itemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = colors.primary,
        selectedTextColor = colors.primary,
        indicatorColor = colors.background,
        unselectedIconColor = colors.onBackground,
        unselectedTextColor = colors.onBackground
    )

    Column {
        HorizontalDivider(color = colors.surface)
        NavigationBar(
            containerColor = colors.background,
            contentColor = colors.onBackground,
            tonalElevation = 0.dp
        ) {
            DaymarkTab.entries.forEach { tab ->
                val selected = tab == selectedTab
                NavigationBarItem(
                    selected = selected,
                    enabled = tab.route != null,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Icon(
                            imageVector = if (selected) tab.filledIcon else tab.outlinedIcon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(tab.label),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = itemColors
                )
            }
        }
    }
}
