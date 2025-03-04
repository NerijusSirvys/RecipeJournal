package com.ns.recipejournal.features.home

import com.ns.recipejournal.features.home.components.ChipFilterOption
import com.ns.recipejournal.features.home.components.RecipeOverview

data class HomeState(
    val recipes: List<RecipeOverview> = emptyList(),
    val categories: List<ChipFilterOption> = emptyList(),
    val cuisines: List<ChipFilterOption> = emptyList()
)
