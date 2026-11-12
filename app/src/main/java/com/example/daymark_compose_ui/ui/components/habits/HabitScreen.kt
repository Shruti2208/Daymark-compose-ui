package com.example.daymark_compose_ui.ui.components.habits

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.Habit
import com.example.daymark_compose_ui.data.SampleData
import com.example.daymark_compose_ui.ui.theme.DaymarkcomposeuiTheme

@Composable
fun HabitsScreen() {
    val sampleData = remember { SampleData() }
    HabitsContent(sampleData = sampleData, habits = sampleData.habits)
}

@Composable
private fun HabitsContent(sampleData: SampleData, habits: List<Habit>?) {
    var selectedDate by rememberSaveable { mutableStateOf("29") }
    var selectedFilter by rememberSaveable { mutableStateOf("All") }
    val colors = MaterialTheme.colorScheme

    val visibleHabits = habits?.filter { selectedFilter == "All" || it.category == selectedFilter }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        item(key = "header") {
            Column {
                Text(
                    text = stringResource(R.string.tab_habits),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("This week")
                    Text("72% . 28/39")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    sampleData.sampleWeek.forEach { (day, date) ->
                        DateCard(
                            day = day,
                            date = date,
                            isSelected = selectedDate == date,
                            onClick = { selectedDate = date },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    sampleData.filters.forEach { filter ->
                        FilterChip(
                            selected = selectedFilter == filter,
                            onClick = { selectedFilter = filter },
                            label = { Text(filter) },
                            shape = CircleShape,
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color(0xFFF1F1F1),
                                selectedContainerColor = Color(0xFFD9D9D9)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        when {
            visibleHabits == null -> item(key = "loading") {
                HabitListMessage(R.string.habits_loading)
            }
            visibleHabits.isEmpty() -> item(key = "empty") {
                HabitListMessage(R.string.habits_empty_filter)
            }
            else -> items(visibleHabits, key = { habit -> "habit:${habit.name}" }) { habit ->
                Column(modifier = Modifier.animateItem()) {
                    HorizontalDivider(color = colors.surface)
                    HabitProgressItem(
                        habit = habit,
                        onClick = { /* Open habit details when available. */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun HabitListMessage(message: Int) {
    Text(
        text = stringResource(message),
        modifier = Modifier.padding(vertical = 16.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview(name = "Habits")
@Composable
private fun HabitsScreenPreview() {
    DaymarkcomposeuiTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HabitsScreen()
        }
    }
}

@Preview(name = "Habits loading")
@Composable
private fun HabitsLoadingPreview() {
    DaymarkcomposeuiTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HabitsContent(sampleData = SampleData(), habits = null)
        }
    }
}
