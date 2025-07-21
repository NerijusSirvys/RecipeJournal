@file:OptIn(ExperimentalMaterial3Api::class)

package com.ns.recipejournal.features.createNew

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ns.recipejournal.features.createNew.bottomNavBar.BottomNavBar
import com.ns.recipejournal.features.createNew.bottomNavBar.BottomNavigationDestination
import com.ns.recipejournal.features.createNew.screens.basicInfo.BasicInfoScreen
import com.ns.recipejournal.features.createNew.screens.ingredients.IngredientsScreen
import com.ns.recipejournal.features.createNew.screens.steps.StepsScreen

@Composable
fun CreateNewRecipe(modifier: Modifier = Modifier) {
   val navController = rememberNavController()

   Scaffold(
      bottomBar = {
         BottomNavBar(
            onNavigate = { navController.navigate(it) }
         )
      }
   ) { innerPadding ->
      NavHost(navController = navController, startDestination = BottomNavigationDestination.BasicInfo, modifier = modifier.padding(innerPadding)) {
         composable<BottomNavigationDestination.BasicInfo> {
            BasicInfoScreen()
         }
         composable<BottomNavigationDestination.Ingredients> {
            IngredientsScreen()
         }
         composable<BottomNavigationDestination.Steps> {
            StepsScreen()
         }
      }
   }
}