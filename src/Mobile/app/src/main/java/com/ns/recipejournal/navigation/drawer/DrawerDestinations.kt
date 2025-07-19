package com.ns.recipejournal.navigation.drawer

import kotlinx.serialization.Serializable

interface DrawerDestinations {
   @Serializable
   data object Parse : DrawerDestinations

   @Serializable
   data object Home : DrawerDestinations

   @Serializable
   data object CreateNew : DrawerDestinations
}