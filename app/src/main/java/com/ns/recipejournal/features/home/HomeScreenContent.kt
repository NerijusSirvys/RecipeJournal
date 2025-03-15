package com.ns.recipejournal.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ns.recipejournal.features.home.components.TopFilterBar
import com.ns.recipejournal.features.home.components.chipFilter.ChipFilter
import com.ns.recipejournal.features.home.components.recipeCard.RecipeCard
import com.ns.recipejournal.features.home.data.FilterType

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    onAction: (HomeScreenAction) -> Unit
) {
    Column(modifier = modifier) {
        TopFilterBar(
            searchQuery = state.searchQuery,
            showFavourites = state.showFavourites,
            onQueryChange = { onAction(HomeScreenAction.UpdateSearch(it)) },
            onFavouriteToggle = { onAction(HomeScreenAction.ToggleFavourites) }
        )

        Spacer(Modifier.height(25.dp))
        ChipFilter(
            name = "Meal Category",
            options = state.categories,
            onClick = { onAction(HomeScreenAction.ToggleFilterOption(it, FilterType.CATEGORY)) }
        )
        Spacer(Modifier.height(10.dp))
        ChipFilter(
            name = "Cuisines",
            options = state.cuisines,
            onClick = { onAction(HomeScreenAction.ToggleFilterOption(it, FilterType.CUISINE)) }
        )
        Spacer(Modifier.height(25.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.recipes, key = { it.id }) {
                RecipeCard(details = it)
            }
        }
    }
}