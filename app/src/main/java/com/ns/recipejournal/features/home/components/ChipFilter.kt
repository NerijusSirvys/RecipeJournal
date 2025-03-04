package com.ns.recipejournal.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
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
                ElevatedFilterChip(
                    selected = it.enabled,
                    onClick = { onClick(it.id) },
                    label = { Text(text = it.text) },
                    leadingIcon = {
                        if (it.enabled)
                            Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    }
                )
            }
        }
    }
}

data class ChipFilterOption(
    val id: String,
    val enabled: Boolean,
    val text: String
)