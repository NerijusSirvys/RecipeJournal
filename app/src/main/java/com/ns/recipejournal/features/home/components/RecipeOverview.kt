package com.ns.recipejournal.features.home.components


data class RecipeOverview(
    val id: String,
    val name: String,
    val cookTime: Int,
    val calories: Int,
    val servings: Int,
    val cuisine: String,
    val category: String,
    val isFavourite: Boolean
)
