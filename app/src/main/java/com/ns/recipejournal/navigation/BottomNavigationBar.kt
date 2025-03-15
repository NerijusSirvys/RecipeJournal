package com.ns.recipejournal.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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

            val color = if (isSelected) MaterialTheme.colorScheme.primary
            else IconButtonDefaults.iconButtonColors().contentColor

            NavigationBarItem(
                label = {
                    Text(
                        text = navItem.name,
                        color = color
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = null,
                        tint = color
                    )
                },
                selected = false,
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