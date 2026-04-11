package com.nsdevelopment.mobile.screens.home

import com.nsdevelopment.mobile.data.Cuisine
import com.nsdevelopment.mobile.data.MealCategory

data class HomeScreenState(
    val mealsCategories: Map<String, Boolean> = buildMap {
        MealCategory.entries.forEach { item -> put(item.name, false) }
    },

    val cuisines: Map<String, Boolean> = buildMap {
        Cuisine.entries.forEach { item -> put(item.name, false) }
    }
)