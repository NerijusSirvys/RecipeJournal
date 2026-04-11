package com.nsdevelopment.mobile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsdevelopment.mobile.ui.theme.Accent
import com.nsdevelopment.mobile.ui.theme.ApplicationTheme
import com.nsdevelopment.mobile.ui.theme.Primary
import com.nsdevelopment.mobile.ui.theme.PrimaryText
import com.nsdevelopment.mobile.ui.theme.Secondary
import com.nsdevelopment.mobile.utilities.SystemPreviews

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    label: String
) {
    FilterChip(
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Accent,
            selectedLabelColor = Secondary,
            containerColor = Primary,
            labelColor = PrimaryText
        ),
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        label = {
            Text(text = label)
        }
    )
}

@SystemPreviews
@Composable
private fun Preview() {
    ApplicationTheme {
        Surface {
            Column(modifier = Modifier.padding(25.dp)) {
                Chip(
                    selected = false,
                    onClick = {},
                    label = "My Chip"
                )

                Chip(
                    selected = true,
                    onClick = {},
                    label = "My Chip"
                )
            }
        }
    }
}