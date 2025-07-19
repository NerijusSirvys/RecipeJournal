package com.ns.recipejournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ns.recipejournal.components.TopBar
import com.ns.recipejournal.extensions.toFriendlyName
import com.ns.recipejournal.features.createNew.CreateNewRecipe
import com.ns.recipejournal.features.home.HomeScreen
import com.ns.recipejournal.features.parse.ParseScreen
import com.ns.recipejournal.navigation.drawer.DrawerDestinations
import com.ns.recipejournal.navigation.drawer.NavigationDrawer
import com.ns.recipejournal.ui.theme.MobileTheme

class MainActivity : ComponentActivity() {
   @OptIn(ExperimentalMaterial3Api::class)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         MobileTheme {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navController = rememberNavController()
            var destinationName by remember { mutableStateOf(DrawerDestinations.Home.toFriendlyName()) }

            NavigationDrawer(
               drawerState = drawerState,
               scope = scope,
               onNavigate = {
                  navController.navigate(it)
                  destinationName = it.toFriendlyName()
               }
            ) {
               Scaffold(
                  modifier = Modifier.fillMaxSize(),
                  topBar = {
                     TopBar(
                        scope = scope,
                        drawerState = drawerState,
                        screenName = destinationName
                     )
                  }
               ) { innerPadding ->

                  NavHost(navController = navController, startDestination = DrawerDestinations.Home, modifier = Modifier.padding(innerPadding)) {
                     composable<DrawerDestinations.Home> {
                        HomeScreen()
                     }

                     composable<DrawerDestinations.CreateNew> {
                        CreateNewRecipe()
                     }

                     composable<DrawerDestinations.Parse> {
                        ParseScreen()
                     }
                  }
               }
            }
         }
      }
   }
}
