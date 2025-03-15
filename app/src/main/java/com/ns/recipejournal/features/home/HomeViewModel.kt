package com.ns.recipejournal.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ns.recipejournal.features.home.components.RecipeOverview
import com.ns.recipejournal.features.home.components.chipFilter.ChipFilterOption
import com.ns.recipejournal.features.home.data.FilterType
import com.ns.recipejournal.services.RecipeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeService: RecipeService
) : ViewModel() {

    private val _recipes = MutableStateFlow(listOf<RecipeOverview>())
    private val _searchQuery = MutableStateFlow("")
    private val _cuisineFilter = MutableStateFlow(listOf<ChipFilterOption>())
    private val _categoryFilter = MutableStateFlow(listOf<ChipFilterOption>())

    private val _state = MutableStateFlow(HomeState())
    val state = combine(_recipes, _searchQuery, _cuisineFilter, _categoryFilter, _state) { recipes, searchQuery, cuisineFilter, categoryFilter, state ->
        state.copy(
            searchQuery = searchQuery,
            recipes = recipeService.filterRecipes(recipes, searchQuery, cuisineFilter, categoryFilter, state.showFavourites),
            cuisines = cuisineFilter,
            categories = categoryFilter
        )
    }.onStart {
        _state.update {
            it.copy(isLoading = true)
        }

        loadData()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())

    private fun loadData() {

        viewModelScope.launch {
            val recipes = recipeService.getRecipes()
            val categories = recipeService.getMealCategories()
            val cuisines = recipeService.getCuisines()

            combine(recipes, categories, cuisines) { r, ca, cu ->
                Triple(r, ca, cu)
            }.collect { (r, ca, cu) ->
                _recipes.update { r }

                _categoryFilter.update {
                    ca.map { ChipFilterOption(it.id, false, it.name) }
                }

                _cuisineFilter.update {
                    cu.map { ChipFilterOption(it.id, false, it.name) }
                }

                _state.update { it.copy(isLoading = false) }
            }
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