package com.ns.recipejournal.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.recipejournal.features.home.components.ChipFilterOption
import com.ns.recipejournal.features.home.components.RecipeOverview
import com.ns.recipejournal.features.home.data.FilterType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _recipes = MutableStateFlow(listOf<RecipeOverview>())
    private val _searchQuery = MutableStateFlow("")
    private val _cuisineFilter = MutableStateFlow(listOf<ChipFilterOption>())
    private val _categoryFilter = MutableStateFlow(listOf<ChipFilterOption>())

    private val _state = MutableStateFlow(HomeState())
    val state = combine(_recipes, _searchQuery, _cuisineFilter, _categoryFilter, _state) { recipes, searchQuery, cuisineFilter, categoryFilter, state ->

        var filtered = recipes.filter { it.name.contains(searchQuery) }
        if (state.showFavourites)
            filtered = filtered.filter { it.isFavourite }

        if (cuisineFilter.any { it.enabled }) {
            val enabled = cuisineFilter.filter { it.enabled }
            filtered = filtered.filter { recipe -> enabled.any { it.id == recipe.cuisine } }
        }

        if (categoryFilter.any { it.enabled }) {
            val enabled = categoryFilter.filter { it.enabled }
            filtered = filtered.filter { recipe -> enabled.any { it.id == recipe.category } }
        }

        state.copy(
            searchQuery = searchQuery,
            recipes = filtered,
            cuisines = cuisineFilter,
            categories = categoryFilter
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())

    init {
        _recipes.update {
            listOf(
                RecipeOverview(
                    id = "1",
                    name = "Recipe 1",
                    calories = 150,
                    category = "cat1",
                    cookTime = 15,
                    servings = 3,
                    cuisine = "cus1",
                    isFavourite = false
                ),
                RecipeOverview(
                    id = "2",
                    name = "Recipe 2",
                    calories = 150,
                    category = "cat1",
                    cookTime = 15,
                    servings = 3,
                    cuisine = "cus1",
                    isFavourite = true
                ),
                RecipeOverview(
                    id = "3",
                    name = "Recipe 3",
                    calories = 150,
                    category = "cat2",
                    cookTime = 15,
                    servings = 3,
                    cuisine = "cus3",
                    isFavourite = true
                ),
                RecipeOverview(
                    id = "4",
                    name = "Recipe 4",
                    calories = 150,
                    category = "cat3",
                    cookTime = 15,
                    servings = 3,
                    cuisine = "cus4",
                    isFavourite = false
                )
            )
        }

        _categoryFilter.update {
            listOf(
                ChipFilterOption("cat1", false, "Breakfast"),
                ChipFilterOption("cat2", false, "Lunch"),
                ChipFilterOption("cat3", false, "Dinner"),
                ChipFilterOption("cat4", false, "Snack"),
            )
        }

        _cuisineFilter.update {
            listOf(
                ChipFilterOption("cus1", false, "American"),
                ChipFilterOption("cus2", false, "Mexican"),
                ChipFilterOption("cus3", false, "Chinese"),
                ChipFilterOption("cus4", false, "Italian"),
                ChipFilterOption("cus5", false, "Lithuanian"),
            )
        }
    }

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.ToggleFilterOption -> toggleFilter(action.id, action.type)
            is HomeScreenAction.UpdateSearch -> updateSearchQuery(action.query)
            is HomeScreenAction.ToggleFavourites -> toggleFavourite()
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    private fun toggleFilter(filterId: String, filterType: FilterType) {

        when (filterType) {
            FilterType.CUISINE -> {
                _cuisineFilter.update {
                    val mutable = it.toMutableList()

                    var option = mutable.find { option -> option.id == filterId }
                    if (option != null) {
                        val index = mutable.indexOf(option)
                        option = option.copy(enabled = !option.enabled)

                        mutable[index] = option
                    }
                    mutable.toList()
                }
            }

            FilterType.CATEGORY -> {
                _categoryFilter.update {
                    val mutable = it.toMutableList()

                    var option = mutable.find { option -> option.id == filterId }
                    if (option != null) {
                        val index = mutable.indexOf(option)
                        option = option!!.copy(enabled = !option!!.enabled)

                        mutable[index] = option!!
                    }
                    mutable.toList()
                }
            }
        }

    }

    private fun toggleFavourite() {
        _state.update {
            it.copy(showFavourites = !it.showFavourites)
        }
    }
}