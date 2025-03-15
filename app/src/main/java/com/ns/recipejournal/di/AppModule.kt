package com.ns.recipejournal.di

import com.ns.recipejournal.features.home.HomeViewModel
import com.ns.recipejournal.services.RecipeService
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::RecipeService)
}