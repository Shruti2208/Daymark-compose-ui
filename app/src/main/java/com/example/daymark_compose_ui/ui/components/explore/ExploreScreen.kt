package com.example.daymark_compose_ui.ui.components.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Grass
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.SampleData
import com.example.daymark_compose_ui.ui.components.CategoryFilterOption
import com.example.daymark_compose_ui.ui.components.CategoryFilterRow
import com.example.daymark_compose_ui.ui.components.FeaturedSectionHeader

@Composable
fun ExploreScreen() {
    var query by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("places") }
    var favoriteIds by rememberSaveable { mutableStateOf(intArrayOf()) }
    val places = remember { SampleData().samplePlaces }
    val showPlaces = selectedCategory == "places"
    val searchTerm = query.trim()
    val visiblePlaces = if (showPlaces) {
        places.filter { place ->
            place.name.contains(searchTerm, ignoreCase = true) ||
                place.location.contains(searchTerm, ignoreCase = true)
        }
    } else {
        emptyList()
    }

    PlacesGrid(
        places = visiblePlaces,
        favoriteIds = favoriteIds,
        onToggleFavorite = { id ->
            favoriteIds = if (id in favoriteIds) {
                favoriteIds.filterNot { it == id }.toIntArray()
            } else {
                favoriteIds + id
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text(
                text = stringResource(R.string.tab_explore),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            ExploreSearchField(query = query, onQueryChange = { query = it })
            Spacer(modifier = Modifier.height(4.dp))
            CategoryFilterRow(
                options = listOf(
                    CategoryFilterOption("places", Icons.Outlined.Place, stringResource(R.string.explore_places)),
                    CategoryFilterOption("reading", Icons.AutoMirrored.Outlined.MenuBook, stringResource(R.string.explore_reading)),
                    CategoryFilterOption("routines", Icons.Outlined.Grass, stringResource(R.string.explore_routines)),
                    CategoryFilterOption("ideas", Icons.Outlined.Edit, stringResource(R.string.explore_ideas))
                ),
                selectedId = selectedCategory,
                onSelect = { selectedCategory = it }
            )
            if (showPlaces) {
                FeaturedSectionHeader(
                    title = stringResource(R.string.explore_featured_places),
                    onSeeAll = { /* Open the full places list when available. */ }
                )
                if (visiblePlaces.isEmpty()) {
                    Text(
                        text = stringResource(R.string.explore_no_places),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun ExploreSearchField(query: String, onQueryChange: (String) -> Unit) {
    val colors = MaterialTheme.colorScheme
    val placeholder = stringResource(R.string.explore_search_hint)
    val textStyle = MaterialTheme.typography.bodyMedium
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = placeholder },
        singleLine = true,
        textStyle = textStyle.copy(color = colors.onSurface),
        cursorBrush = SolidColor(colors.primary),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .background(lerp(colors.background, colors.surface, 0.35f), CircleShape)
                    .border(1.dp, colors.surface, CircleShape)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = colors.onSurfaceVariant
                )
                Box(modifier = Modifier.weight(1f)) {
                    if (query.isEmpty()) {
                        Text(
                            text = placeholder,
                            modifier = Modifier.clearAndSetSemantics {},
                            style = textStyle,
                            color = colors.onSurfaceVariant
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}
