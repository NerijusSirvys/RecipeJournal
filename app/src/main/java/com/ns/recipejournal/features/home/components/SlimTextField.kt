package com.ns.recipejournal.features.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ns.recipejournal.ui.theme.RecipeJournalTheme

@Composable
fun SlimTextField(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    text: String,
    placeholder: String = ""
) {
    BasicTextField(
        value = text,
        onValueChange = onQueryChange,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
        singleLine = true,
        modifier = modifier
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onBackground, shape = MaterialTheme.shapes.extraSmall),
    ) { innerTextField ->
        Box(
            modifier = modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .padding(top = 3.dp)
        ) {
            if (text.isEmpty()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false,
                )
            }
            innerTextField()
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    RecipeJournalTheme {
        Surface {
            SlimTextField(text = "Test", onQueryChange = {})
        }
    }
}