package com.ns.recipejournal.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ns.recipejournal.R

val navItems = listOf(
    NavItem(name = "Create", route = Routes.Create, R.drawable.create_outline),
    NavItem(name = "Home", route = Routes.Home, R.drawable.home_outline),
    NavItem(name = "Parse", route = Routes.Parse, R.drawable.parse_outline)
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navItems.forEach { navItem ->

            val isSelected =
                currentDestination?.hierarchy?.any { it.route == navItem.route::class.qualifiedName } == true

            NavigationBarItem(
                label = { Text(text = navItem.name) },
                icon = { Icon(painterResource(navItem.icon), contentDescription = null) },
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}