package com.ns.recipejournal.features.home

import com.ns.recipejournal.features.home.components.RecipeOverview
import com.ns.recipejournal.features.home.components.chipFilter.ChipFilterOption

data class HomeState(
    val recipes: List<RecipeOverview> = emptyList(),
    val categories: List<ChipFilterOption> = emptyList(),
    val cuisines: List<ChipFilterOption> = emptyList(),
    val searchQuery: String = "",
    val showFavourites: Boolean = false,
    val isLoading: Boolean = false
)
