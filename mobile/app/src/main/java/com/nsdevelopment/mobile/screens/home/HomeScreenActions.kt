package com.nsdevelopment.mobile.screens.home

import com.nsdevelopment.mobile.components.slidingButton.SlidingButtonOption

sealed interface HomeScreenActions {
    data class ToggleMealCategoriesFilter(val filter: String) : HomeScreenActions
    data object ResetMealCategoriesFilter : HomeScreenActions
    data class ToggleCuisineFilter(val filter: String) : HomeScreenActions
    data object ResetCuisineFilters : HomeScreenActions
    data class ToggleFavouriteFilter(val filter: SlidingButtonOption) : HomeScreenActions
}