package com.ns.recipejournal.features.createNew.bottomNavBar

import kotlinx.serialization.Serializable

interface BottomNavigationDestination {
   @Serializable
   data object BasicInfo : BottomNavigationDestination

   @Serializable
   data object Ingredients : BottomNavigationDestination

   @Serializable
   data object Steps : BottomNavigationDestination
}