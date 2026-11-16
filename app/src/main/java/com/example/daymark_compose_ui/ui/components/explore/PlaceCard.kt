package com.example.daymark_compose_ui.ui.components.explore

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.daymark_compose_ui.R
import com.example.daymark_compose_ui.data.Place

@Composable
internal fun PlaceCard(
    place: Place,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colors.surfaceContainerLowest),
        border = BorderStroke(1.dp, colors.surface)
    ) {
        Box {
            PlaceImage(place, Modifier.fillMaxWidth().aspectRatio(1.55f))
            FavoriteButton(
                placeName = place.name,
                isFavorite = isFavorite,
                onToggle = onToggleFavorite,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = place.location,
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceVariant
            )
            RatingRow(place)
        }
    }
}

@Composable
internal fun PlaceImage(place: Place, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(place.imageRes),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
internal fun FavoriteButton(
    placeName: String,
    isFavorite: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    overImage: Boolean = true
) {
    IconToggleButton(checked = isFavorite, onCheckedChange = { onToggle() }, modifier = modifier) {
        Box(
            modifier = Modifier.size(32.dp).background(if (overImage) Color.Black.copy(alpha = 0.45f) else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(
                    if (isFavorite) R.string.place_remove_favorite else R.string.place_add_favorite,
                    placeName
                ),
                modifier = Modifier.size(19.dp),
                tint = if (overImage) Color.White else MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
internal fun RatingRow(
    place: Place,
    @StringRes reviewCountRes: Int = R.string.place_rating
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = Color(0xFFAD6800)
        )
        Text(
            text = stringResource(reviewCountRes, place.rating, place.reviewCount),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
