package com.ns.recipejournal.navigation.drawer

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ns.recipejournal.constants.ContentDescriptionConstants

@Composable
fun DrawerItem(
   modifier: Modifier = Modifier,
   label: String,
   @DrawableRes iconId: Int,
   onClick: () -> Unit,
) {
   NavigationDrawerItem(
      modifier = modifier,
      label = { Text(text = label) },
      onClick = onClick,
      selected = false,
      icon = {
         Icon(painter = painterResource(iconId), contentDescription = ContentDescriptionConstants.MENU_ICON)
      }
   )
}