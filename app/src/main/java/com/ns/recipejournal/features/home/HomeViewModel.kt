package com.ns.recipejournal.features.home

import androidx.lifecycle.ViewModel
import com.ns.recipejournal.features.home.components.ChipFilterOption
import com.ns.recipejournal.features.home.components.RecipeOverview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    init {
        _state.update {
            it.copy(
                categories = listOf(
                    ChipFilterOption("cat1", false, "breakfast"),
                    ChipFilterOption("cat2", false, "lunch"),
                    ChipFilterOption("cat3", false, "dinner"),
                    ChipFilterOption("cat4", false, "snack"),
                ),
                cuisines = listOf(
                    ChipFilterOption("cus1", false, "american"),
                    ChipFilterOption("cus2", false, "mexican"),
                    ChipFilterOption("cus3", false, "chinese"),
                    ChipFilterOption("cus4", false, "italian"),
                    ChipFilterOption("cus5", false, "lithuanian"),
                ),
                recipes = listOf(
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
            )
        }
    }

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.ToggleFavourite -> toggleFavourite(action.id)
            is HomeScreenAction.ToggleFilterOption -> toggleFilter(action.id)
        }
    }

    private fun toggleFilter(filterId: String) {
        TODO("Not yet implemented")
    }

    private fun toggleFavourite(recipeId: String) {
        _state.update {
            val recipes = it.recipes.toMutableList().apply {
                var recipe = this.find { recipes -> recipes.id == recipeId }
                if (recipe == null) return@apply

                val index = this.indexOf(recipe)
                recipe = recipe.copy(isFavourite = !recipe.isFavourite)

                this[index] = recipe
            }.toList()




            it.copy(recipes = recipes)
        }
    }
}