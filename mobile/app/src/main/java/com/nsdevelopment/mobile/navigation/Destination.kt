package com.nsdevelopment.mobile.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object ImportRecipe : Destination

    @Serializable
    data object AddRecipe : Destination

    @Serializable
    data object Home : Destination
}
