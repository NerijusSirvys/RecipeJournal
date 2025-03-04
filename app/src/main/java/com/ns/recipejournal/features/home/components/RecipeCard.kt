package com.ns.recipejournal.features.home.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ns.recipejournal.R
import com.ns.recipejournal.ui.theme.RecipeJournalTheme

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    onToggleFavourite: () -> Unit,
    details: RecipeOverview
) {
    Card(
        modifier = modifier.heightIn(max = 100.dp)
    ) {
        Row {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.food_image),
                contentDescription = null,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .weight(3.5f)
                    .height(IntrinsicSize.Min)
                    .padding(7.dp, 7.dp)
            ) {
                TopRow(
                    cuisine = details.cuisine,
                    favourite = details.isFavourite,
                    onToggleFavourite = onToggleFavourite
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = details.name, style = MaterialTheme.typography.titleLarge)
                    BottomRow(details.cookTime, details.calories, details.servings)
                }
            }
        }
    }
}

@Composable
fun BottomRow(time: Int, calories: Int, servings: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {

        InfoTag(iconId = R.drawable.time_outline, text = "$time min")
        InfoTag(iconId = R.drawable.fire_outline, text = "$calories kcal")
        InfoTag(iconId = R.drawable.food, text = "$servings servings")
    }
}

@Composable
private fun TopRow(
    onToggleFavourite: () -> Unit,
    cuisine: String,
    favourite: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.maximise),
                contentDescription = null,
                modifier = Modifier
                    .rotate(90f)
                    .size(20.dp)
                    .offset(y = 15.dp)
            )

            Text(
                text = cuisine,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.offset(x = -15.dp)
            )
        }

        Icon(
            modifier = Modifier.clickable(onClick = onToggleFavourite),
            painter = painterResource(R.drawable.add_outline),
            contentDescription = null,
            tint = if (favourite) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    device = Devices.PIXEL_6,
)
@Composable
private fun Preview() {
    RecipeJournalTheme {

        val details = RecipeOverview(
            id = "1",
            name = "Recipe 1",
            calories = 150,
            category = "Breakfast",
            cookTime = 15,
            servings = 3,
            cuisine = "American",
            isFavourite = true
        )

        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            RecipeCard(details = details, onToggleFavourite = {})
        }
    }
}