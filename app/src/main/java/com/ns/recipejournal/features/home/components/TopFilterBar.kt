package com.ns.recipejournal.features.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TopFilterBar(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onFavouriteToggle: () -> Unit,
    searchQuery: String,
    showFavourites: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        SlimTextField(
            modifier = Modifier.weight(1f),
            text = searchQuery,
            onQueryChange = onQueryChange,
            placeholder = "Search"
        )
        IconButton(onClick = onFavouriteToggle) {
            Icon(
                imageVector = if (showFavourites) Icons.Default.Favorite
                else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = if (showFavourites) MaterialTheme.colorScheme.primary
                else IconButtonDefaults.iconButtonColors().contentColor
            )
        }
    }
}