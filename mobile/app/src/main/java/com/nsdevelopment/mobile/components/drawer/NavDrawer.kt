package com.nsdevelopment.mobile.components.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nsdevelopment.mobile.Destination
import com.nsdevelopment.mobile.R
import com.nsdevelopment.mobile.ui.theme.ApplicationTheme
import com.nsdevelopment.mobile.utilities.SystemPreviews

var navDrawerItems = listOf(
    DrawerItem("Home", icon = R.drawable.ic_home, Destination.Home),
    DrawerItem("Add New", icon = R.drawable.ic_note_add, Destination.AddRecipe),
    DrawerItem("Import", icon = R.drawable.ic_globe, Destination.ImportRecipe)
)

data class DrawerItem(
    val label: String,
    val icon: Int,
    val destination: Destination
)

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onClick: (DrawerItem) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerShape = MaterialTheme.shapes.extraLarge) {
                navDrawerItems.forEach { item ->
                    NavDrawerItem(
                        label = item.label,
                        onClick = { onClick.invoke(item) },
                        selected = false,
                        iconId = item.icon
                    )
                }
            }
        }
    ) { content() }
}

@SystemPreviews
@Composable
private fun Preview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    ApplicationTheme() {
        NavDrawer(
            drawerState = drawerState,
            onClick = {},
            content = {}
        )
    }
}