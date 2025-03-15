package com.ns.recipejournal.features.home.components.recipeCard

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ns.recipejournal.R
import com.ns.recipejournal.features.home.components.CardChip
import com.ns.recipejournal.features.home.components.FavouriteIndicator
import com.ns.recipejournal.features.home.components.InfoTag
import com.ns.recipejournal.features.home.components.RecipeOverview
import com.ns.recipejournal.ui.theme.RecipeJournalTheme

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    details: RecipeOverview
) {
    Card(
        modifier = modifier.heightIn(max = 100.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            FavouriteIndicator(isFavourite = details.isFavourite)
            Row {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.food_image),
                    contentDescription = null,
                    modifier = Modifier.weight(1f),
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .weight(3.5f)
                        .height(IntrinsicSize.Min)
                        .padding(7.dp, 7.dp)
                ) {
                    TopRow {
                        CardChip(text = details.cuisine)
                        CardChip(text = details.category)
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(13.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = details.name,
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = false
                        )
                        BottomRow {
                            InfoTag(iconId = R.drawable.time_outline, text = "${details.cookTime} min")
                            InfoTag(iconId = R.drawable.fire_outline, text = "${details.calories} kcal")
                            InfoTag(iconId = R.drawable.food, text = "${details.servings} servings")
                        }
                    }
                }
            }
        }
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
            RecipeCard(details = details)
        }
    }
}