package com.ns.recipejournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ns.recipejournal.features.home.HomeScreen
import com.ns.recipejournal.navigation.BottomNavigationBar
import com.ns.recipejournal.navigation.Routes
import com.ns.recipejournal.ui.theme.RecipeJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeJournalTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 15.dp)
                    ) {
                        NavHost(navController = navController, startDestination = Routes.Home) {
                            composable<Routes.Create> {}
                            composable<Routes.Home> { HomeScreen() }
                            composable<Routes.Parse> {}
                        }
                    }
                }
            }
        }
    }
}
