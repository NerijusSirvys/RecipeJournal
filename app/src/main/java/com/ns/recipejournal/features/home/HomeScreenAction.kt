package com.ns.recipejournal.features.home

import com.ns.recipejournal.features.home.data.FilterType

sealed interface HomeScreenAction {
    data class ToggleFilterOption(val id: String, val type: FilterType) : HomeScreenAction
    data class UpdateSearch(val query: String) : HomeScreenAction
    data object ToggleFavourites : HomeScreenAction
}