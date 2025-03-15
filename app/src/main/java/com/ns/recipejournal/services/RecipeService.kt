package com.ns.recipejournal.services

import com.ns.recipejournal.features.home.components.RecipeOverview
import com.ns.recipejournal.features.home.components.chipFilter.ChipFilterOption
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeService {
    suspend fun getCuisines(): Flow<List<Cuisine>> {
        delay(100)
        return flow {
            emit(
                listOf(
                    Cuisine("cus1", "American"),
                    Cuisine("cus2", "Mexican"),
                    Cuisine("cus3", "Chinese"),
                    Cuisine("cus4", "Italian"),
                    Cuisine("cus5", "Lithuanian"),
                )
            )
        }
    }

    suspend fun getMealCategories(): Flow<List<MealCategory>> {
        delay(250)
        return flow {
            emit(
                listOf(
                    MealCategory("cat1", "Breakfast"),
                    MealCategory("cat2", "Lunch"),
                    MealCategory("cat3", "Dinner"),
                    MealCategory("cat4", "Snack"),
                )
            )
        }
    }

    suspend fun getRecipes(): Flow<List<RecipeOverview>> {
        delay(500)
        return flow {
            emit(
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
            )
        }
    }

    fun filterRecipes(
        recipes: List<RecipeOverview>,
        searchQuery: String,
        cuisineFilter: List<ChipFilterOption>,
        categoryFilter: List<ChipFilterOption>,
        showFavourites: Boolean
    ): List<RecipeOverview> {
        var filtered = recipes.filter { it.name.contains(searchQuery) }
        if (showFavourites)
            filtered = filtered.filter { it.isFavourite }

        if (cuisineFilter.any { it.enabled }) {
            val enabled = cuisineFilter.filter { it.enabled }
            filtered = filtered.filter { recipe -> enabled.any { it.id == recipe.cuisine } }
        }

        if (categoryFilter.any { it.enabled }) {
            val enabled = categoryFilter.filter { it.enabled }
            filtered = filtered.filter { recipe -> enabled.any { it.id == recipe.category } }
        }

        return filtered
    }
}


data class Cuisine(
    val id: String,
    val name: String
)

data class MealCategory(
    val id: String,
    val name: String
)