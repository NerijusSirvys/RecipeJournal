package com.nsdevelopment.mobile.components.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nsdevelopment.mobile.R

var navDrawerItems = listOf(
    DrawerItem("Home", icon = R.drawable.ic_home),
    DrawerItem("Add New", icon = R.drawable.ic_note_add),
    DrawerItem("Parse", icon = R.drawable.ic_globe)
)

data class DrawerItem(
    val label: String,
    val icon: Int
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
                navDrawerItems.forEachIndexed { index, item ->
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