package com.example.daymark_compose_ui.ui.components.explore.placeDetails

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.Hiking
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.Place
import com.example.daymark_compose_ui.ui.components.StatItem
import com.example.daymark_compose_ui.ui.components.StatsRow
import com.example.daymark_compose_ui.ui.components.explore.FavoriteButton
import com.example.daymark_compose_ui.ui.components.explore.PlaceImage
import com.example.daymark_compose_ui.ui.components.explore.RatingRow

@Composable
internal fun PlaceDetailsScreen(
    place: Place?,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, stringResource(R.string.place_back))
            }
            Spacer(Modifier.weight(1f))
            if (place != null) {
                FavoriteButton(place.name, isFavorite, onToggleFavorite, overImage = false)
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "${place.name}\n${place.location}")
                    }
                    context.startActivity(Intent.createChooser(intent, null))
                }) {
                    Icon(Icons.Outlined.Share, stringResource(R.string.place_share))
                }
            }
        }

        if (place == null) {
            Text(
                text = stringResource(R.string.place_unavailable),
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            return
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PlaceImage(
                place = place,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.65f)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(place.name, style = MaterialTheme.typography.headlineLarge)
                    RatingRow(place, reviewCountRes = R.string.place_detail_rating)
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    place.tags.ifEmpty { listOf(place.location) }.forEach { tag ->
                        val icon = when (tag) {
                            "Hiking" -> Icons.Outlined.Hiking
                            "Nature" -> Icons.Outlined.Forest
                            else -> Icons.Outlined.Place
                        }
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(tag, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
                place.description?.let { Text(it, style = MaterialTheme.typography.bodyMedium) }
                PlaceStats(place)
            }
        }

        Button(
            onClick = onToggleFavorite,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(
                if (isFavorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = stringResource(if (isFavorite) R.string.place_saved else R.string.place_save),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun PlaceStats(place: Place) {
    val stats = listOf(
        Triple(R.string.place_elevation, place.elevation, Icons.Outlined.Terrain),
        Triple(R.string.place_trail_length, place.trailLength, Icons.Outlined.Route),
        Triple(R.string.place_estimated_time, place.estimatedTime, Icons.Outlined.AccessTime)
    ).mapNotNull { (label, value, icon) ->
        value?.let { StatItem(stringResource(label), it, icon) }
    }
    StatsRow(stats)
}
