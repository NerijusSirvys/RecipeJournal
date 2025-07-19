package com.ns.recipejournal.extensions

import com.ns.recipejournal.constants.TextConstants
import com.ns.recipejournal.navigation.drawer.DrawerDestinations
import com.ns.recipejournal.navigation.drawer.DrawerDestinations.CreateNew
import com.ns.recipejournal.navigation.drawer.DrawerDestinations.Home
import com.ns.recipejournal.navigation.drawer.DrawerDestinations.Parse

fun DrawerDestinations.toFriendlyName(): String {
   return when (this) {
      Parse -> TextConstants.PARSE
      Home -> TextConstants.HOME
      CreateNew -> TextConstants.CREATE_NEW
      else -> throw NotImplementedError()
   }
}