package com.nsdevelopment.mobile.screens.home

import androidx.lifecycle.ViewModel
import com.nsdevelopment.mobile.components.slidingButton.SlidingButtonOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewmodel : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeScreenActions) {
        when (action) {
            is HomeScreenActions.ToggleMealCategoriesFilter -> toggleMealCategoryFilter(action.filter)
            HomeScreenActions.ResetMealCategoriesFilter -> resetMealCategoryFilter()
            HomeScreenActions.ResetCuisineFilters -> resetCuisineFilter()
            is HomeScreenActions.ToggleCuisineFilter -> toggleCuisineFilter(action.filter)
            is HomeScreenActions.ToggleFavouriteFilter -> toggleFavouriteFilter(action.filter)
        }
    }

    private fun toggleFavouriteFilter(filter: SlidingButtonOption) {
        TODO("Not yet implemented")
    }

    private fun toggleCuisineFilter(filter: String) {
        _state.update {
            val mutable = it.cuisines.toMutableMap()
            val filterValue = mutable[filter] ?: return
            mutable[filter] = !filterValue
            it.copy(cuisines = mutable)
        }
    }

    fun resetCuisineFilter() {
        _state.update {
            val mutable = it.cuisines.toMutableMap()
            it.cuisines.forEach { (key, _) ->
                mutable[key] = false
            }

            it.copy(cuisines = mutable)
        }
    }

    fun resetMealCategoryFilter() {
        _state.update {
            val mutable = it.mealsCategories.toMutableMap()
            it.mealsCategories.forEach { (key, _) ->
                mutable[key] = false
            }

            it.copy(mealsCategories = mutable)
        }
    }

    fun toggleMealCategoryFilter(filter: String) {
        _state.update {
            val mutable = it.mealsCategories.toMutableMap()
            val filterValue = mutable[filter] ?: return
            mutable[filter] = !filterValue
            it.copy(mealsCategories = mutable)
        }
    }
}