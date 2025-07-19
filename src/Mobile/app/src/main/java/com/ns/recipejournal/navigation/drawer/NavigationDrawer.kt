package com.ns.recipejournal.navigation.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ns.recipejournal.R
import com.ns.recipejournal.constants.TextConstants
import com.ns.recipejournal.extensions.CloseInScope
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawer(
   modifier: Modifier = Modifier,
   drawerState: DrawerState,
   scope: CoroutineScope,
   onNavigate: (DrawerDestinations) -> Unit,
   content: @Composable () -> Unit
) {
   ModalNavigationDrawer(
      modifier = modifier,
      drawerState = drawerState,
      drawerContent = {
         ModalDrawerSheet {
            DrawerItem(
               label = TextConstants.HOME,
               iconId = R.drawable.home_icon,
               onClick = {
                  onNavigate(DrawerDestinations.Home)
                  drawerState.CloseInScope(scope)
               }
            )

            DrawerItem(
               label = TextConstants.CREATE_NEW,
               iconId = R.drawable.create_new_icon,
               onClick = {
                  onNavigate.invoke(DrawerDestinations.CreateNew)
                  drawerState.CloseInScope(scope)
               }
            )

            DrawerItem(
               label = TextConstants.PARSE,
               iconId = R.drawable.parse_icon,
               onClick = {
                  onNavigate.invoke(DrawerDestinations.Parse)
                  drawerState.CloseInScope(scope)
               }
            )
         }
      }
   ) {
      content.invoke()
   }
}