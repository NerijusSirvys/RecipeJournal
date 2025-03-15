package com.ns.recipejournal.features.home.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteIndicator(
    modifier: Modifier = Modifier,
    isFavourite: Boolean
) {
    if (isFavourite) {
        VerticalDivider(
            modifier = modifier,
            thickness = 5.dp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}