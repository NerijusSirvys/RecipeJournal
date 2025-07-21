package com.ns.recipejournal.features.createNew.bottomNavBar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.ns.recipejournal.R
import com.ns.recipejournal.constants.ContentDescriptionConstants
import com.ns.recipejournal.constants.TextConstants

@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
   modifier: Modifier = Modifier,
   onNavigate: (BottomNavigationDestination) -> Unit
) {
   var currentDestination by remember { mutableStateOf<BottomNavigationDestination>(BottomNavigationDestination.BasicInfo) }

   NavigationBar(
      modifier = modifier,
   ) {
      NavBarItem(
         isSelected = currentDestination == BottomNavigationDestination.BasicInfo,
         iconId = R.drawable.details_icon,
         iconDescription = ContentDescriptionConstants.RECIPE_DETAILS_ICON,
         label = TextConstants.BASIC_INFO,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.BasicInfo)
            currentDestination = BottomNavigationDestination.BasicInfo
         }
      )

      NavBarItem(
         isSelected = currentDestination == BottomNavigationDestination.Ingredients,
         iconId = R.drawable.ingredients_icon,
         iconDescription = ContentDescriptionConstants.RECIPE_INGREDIENTS_ICON,
         label = TextConstants.INGREDIENTS,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.Ingredients)
            currentDestination = BottomNavigationDestination.Ingredients
         }
      )

      NavBarItem(
         isSelected = currentDestination == BottomNavigationDestination.Steps,
         iconId = R.drawable.steps_icon,
         iconDescription = ContentDescriptionConstants.RECIPE_STEPS_ICON,
         label = TextConstants.STEPS,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.Steps)
            currentDestination = BottomNavigationDestination.Steps
         }
      )
   }
}

@Composable
fun RowScope.NavBarItem(
   isSelected: Boolean,
   @DrawableRes iconId: Int,
   iconDescription: String,
   label: String,
   onClick: () -> Unit
) {
   NavigationBarItem(
      icon = {
         Icon(painter = painterResource(iconId), contentDescription = iconDescription)
      },
      colors = NavigationBarItemDefaults.colors().copy(
         selectedIndicatorColor = Color.Transparent
      ),
      selected = isSelected,
      onClick = onClick,
      label = {
         Text(text = label)
      },
   )
}
