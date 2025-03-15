package com.ns.recipejournal.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ns.recipejournal.ui.theme.RecipeJournalTheme

@Composable
fun CardChip(
    modifier: Modifier = Modifier,
    text: String
) {
    Surface(
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface),
        color = Color.Transparent,
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp)
        )
    }
}


@Preview
@Composable
private fun CardChipPreview() {
    RecipeJournalTheme {
        CardChip(
            text = "My Text",
        )
    }
}