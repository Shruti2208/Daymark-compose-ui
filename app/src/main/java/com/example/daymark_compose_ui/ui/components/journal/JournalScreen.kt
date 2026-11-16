package com.example.daymark_compose_ui.ui.components.journal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.JournalEntry
import com.example.daymark_compose_ui.data.SampleData
import com.example.daymark_compose_ui.ui.components.habits.DateCard
import com.example.daymark_compose_ui.ui.theme.DaymarkcomposeuiTheme
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun JournalScreen(
    journalEntries: List<JournalEntry>,
    onAddEntry: () -> Unit = {}
) {
    // Save a timestamp across recreation and start on the sample entries' month.
    var selectedDay by rememberSaveable {
        mutableStateOf(Calendar.getInstance().apply {
            // Clear inherited fields so the selected date starts at local midnight.
            clear()
            set(2026, Calendar.JANUARY, 29)
        }.timeInMillis)
    }
    val selectedDate = Calendar.getInstance().apply { timeInMillis = selectedDay }
    val locale = Locale.getDefault()
    // Entry dates use yyyy-MM-dd, so the year-month prefix matches the entire month.
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    val monthPrefix = dateFormat.format(selectedDate.time).take(7) + "-"
    val visibleEntries = journalEntries.filter { it.date.startsWith(monthPrefix) }
    val weekStart = (selectedDate.clone() as Calendar).apply {
        // Calendar numbers Sunday first. Modulo maps Monday to 0 and Sunday to 6.
        val daysSinceMonday = (get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7
        add(Calendar.DAY_OF_MONTH, -daysSinceMonday)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 88.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(key = "header") {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.tab_journal),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Search, stringResource(R.string.journal_search))
                    }
                }
            }
            item(key = "dateSelector") {
                // Calendar.add handles year rollover and adjusts dates in shorter months.
                JournalMonthSelector(
                    month = selectedDate.get(Calendar.MONTH),
                    year = selectedDate.get(Calendar.YEAR),
                    onPrevious = {
                        selectedDay = (selectedDate.clone() as Calendar).apply {
                            add(Calendar.MONTH, -1)
                        }.timeInMillis
                    },
                    onNext = {
                        selectedDay = (selectedDate.clone() as Calendar).apply {
                            add(Calendar.MONTH, 1)
                        }.timeInMillis
                    },
                    onMonthSelected = { month ->
                        // The dropdown month is zero-based and belongs to the displayed year.
                        selectedDay = (selectedDate.clone() as Calendar).apply {
                            add(Calendar.MONTH, month - get(Calendar.MONTH))
                        }.timeInMillis
                    }
                )
            }
            item(key = "date") {
                Row(modifier = Modifier.fillMaxWidth().selectableGroup(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    val weekdayFormat = SimpleDateFormat("EEE", locale)
                    val dayFormat = SimpleDateFormat("d", locale)
                    // Build each cell from Monday so the strip can span month or year boundaries.
                    repeat(7) { offset ->
                        val date = (weekStart.clone() as Calendar).apply {
                            add(Calendar.DAY_OF_MONTH, offset)
                        }
                        DateCard(
                            day = weekdayFormat.format(date.time),
                            date = dayFormat.format(date.time),
                            isSelected = date.timeInMillis == selectedDay,
                            onClick = { selectedDay = date.timeInMillis },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
            if (visibleEntries.isEmpty()) {
                item(key = "empty") {
                    Text(
                        text = stringResource(R.string.journal_empty_month),
                        modifier = Modifier.padding(vertical = 12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            items(visibleEntries, key = { it.id }) { entry ->
                JournalEntryCard(entry)
            }
        }
        FloatingActionButton(
            onClick = onAddEntry,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = stringResource(R.string.journal_add_entry)
            )
        }
    }
}

@Composable
fun JournalEntryCard(journalEntry: JournalEntry, modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    val locale = Locale.getDefault()
    val displayDate = remember(journalEntry.date, locale) {
        // Parse the stored ISO date and format the card label in the device locale.
        try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).apply { isLenient = false }
            val date = parser.parse(journalEntry.date)
            date?.let { SimpleDateFormat("EEE, MMM d", locale).format(it) } ?: journalEntry.date
        } catch (_: ParseException) {
            journalEntry.date
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colors.surfaceContainerLowest),
        border = BorderStroke(1.dp, colors.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(journalEntry.imageRes),
                contentDescription = null,
                modifier = Modifier.size(76.dp).clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(displayDate, style = MaterialTheme.typography.labelSmall, color = colors.onSurfaceVariant)
                Text(
                    text = journalEntry.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = journalEntry.body,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            JournalEntryMenu(journalEntry)
        }
    }
}

@Composable
private fun JournalEntryMenu(entry: JournalEntry) {
    Box {
        IconButton(onClick = { }, modifier = Modifier.size(48.dp)) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = stringResource(R.string.journal_entry_options, entry.title),
                modifier = Modifier.size(18.dp)
            )
        }

    }
}
@Composable
fun JournalMonthSelector(
    month: Int,
    year: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onMonthSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val months = DateFormatSymbols.getInstance().months.take(12)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrevious) {
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.journal_previous_month)
            )
        }

        Box {
            TextButton(onClick = { expanded = true }) {
                Text("${months[month]} $year")
                Icon(Icons.Outlined.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                months.forEachIndexed { index, name ->
                    DropdownMenuItem(
                        text = { Text(name) },
                        onClick = {
                            onMonthSelected(index)
                            expanded = false
                        }
                    )
                }
            }
        }

        IconButton(onClick = onNext) {
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = stringResource(R.string.journal_next_month)
            )
        }
    }
}
@Preview(name = "Journal")
@Composable
private fun JournalScreenPreview() {
    DaymarkcomposeuiTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            JournalScreen(SampleData().sampleJournalEntries)
        }
    }
}
