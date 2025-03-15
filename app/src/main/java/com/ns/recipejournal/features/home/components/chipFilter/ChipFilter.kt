package com.ns.recipejournal.features.home.components.chipFilter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipFilter(
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit,
    name: String,
    options: List<ChipFilterOption>
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(options, key = { it.id }) {
                FilterChip(
                    selected = it.enabled,
                    onClick = { onClick(it.id) },
                    label = { Text(text = it.text) },
                    colors = FilterChipDefaults.filterChipColors().copy(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}

