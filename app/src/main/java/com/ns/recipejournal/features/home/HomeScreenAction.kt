package com.ns.recipejournal.features.home

sealed interface HomeScreenAction {
    data class ToggleFilterOption(val id: String) : HomeScreenAction
    data class ToggleFavourite(val id: String) : HomeScreenAction
}