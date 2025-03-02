package com.ns.recipejournal.navigation

import androidx.annotation.DrawableRes

data class NavItem<T : Any>(
    val name: String,
    val route: T,
    @DrawableRes val icon: Int
)

