package com.example.daymark_compose_ui.ui.components.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.data.Place

@Composable
internal fun PlacesGrid(
    places: List<Place>,
    favoriteIds: IntArray,
    onToggleFavorite: (Int) -> Unit,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(key = "header", span = { GridItemSpan(maxLineSpan) }) { header() }
        items(places, key = { it.id }) { place ->
            PlaceCard(
                place = place,
                isFavorite = place.id in favoriteIds,
                onToggleFavorite = { onToggleFavorite(place.id) }
            )
        }
    }
}
