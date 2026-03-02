package com.nsdevelopment.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nsdevelopment.mobile.components.ScreenTopBar
import com.nsdevelopment.mobile.components.drawer.NavDrawer
import com.nsdevelopment.mobile.ui.theme.ApplicationTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        enableEdgeToEdge()
        setContent {
            ApplicationTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val backStackEntry by navController.currentBackStackEntryAsState()

                val title = remember(backStackEntry) {
                    when (backStackEntry?.destination?.route?.split('.')?.last()) {
                        Destination.Home.toString() -> "Home"
                        Destination.AddRecipe.toString() -> "Add Recipe"
                        Destination.ImportRecipe.toString() -> "Import Recipe"
                        else -> ""
                    }
                }


                NavDrawer(
                    drawerState = drawerState,
                    onClick = { item ->
                        navController.navigate(item.destination)
                        scope.launch { drawerState.close() }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            ScreenTopBar(
                                title = title,
                                onMenuClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Destination.Home,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable<Destination.Home>() { Text("Home") }
                            composable<Destination.AddRecipe>() { Text("Add Recipe") }
                            composable<Destination.ImportRecipe>() { Text("Import Recipe") }
                        }
                    }
                }
            }
        }
    }
}


sealed interface Destination {

    @Serializable
    data object ImportRecipe : Destination

    @Serializable
    data object AddRecipe : Destination

    @Serializable
    data object Home : Destination
}
