package com.ns.recipejournal.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
   modifier: Modifier = Modifier,
   scope: CoroutineScope,
   screenName: String,
   drawerState: DrawerState
) {
   TopAppBar(
      modifier = modifier,
      title = { Text(text = screenName) },
      navigationIcon = {
         IconButton(onClick = {
            scope.launch {
               drawerState.open()
            }
         }) {
            Icon(
               imageVector = Icons.Default.Menu, contentDescription = null
            )
         }
      }
   )
}