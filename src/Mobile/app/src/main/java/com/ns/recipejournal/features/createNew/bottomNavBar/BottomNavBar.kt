package com.ns.recipejournal.features.createNew.bottomNavBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ns.recipejournal.R
import com.ns.recipejournal.constants.TextConstants

@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
   modifier: Modifier = Modifier,
   onNavigate: (BottomNavigationDestination) -> Unit
) {

   var currentDestination by remember { mutableStateOf<BottomNavigationDestination>(BottomNavigationDestination.BasicInfo) }

   NavigationBar(
      modifier = modifier
   ) {
      NavigationBarItem(
         selected = currentDestination == BottomNavigationDestination.BasicInfo,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.BasicInfo)
            currentDestination = BottomNavigationDestination.BasicInfo
         },
         icon = {
            Icon(painter = painterResource(R.drawable.details_icon), contentDescription = null)
         },
         label = { Text(text = TextConstants.BASIC_INFO) }
      )

      NavigationBarItem(
         selected = currentDestination == BottomNavigationDestination.Ingredients,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.Ingredients)
            currentDestination = BottomNavigationDestination.Ingredients
         },
         icon = {
            Icon(painter = painterResource(R.drawable.ingredients_icon), contentDescription = null)
         },
         label = { Text(text = TextConstants.INGREDIENTS) }
      )

      NavigationBarItem(
         selected = currentDestination == BottomNavigationDestination.Steps,
         onClick = {
            onNavigate.invoke(BottomNavigationDestination.Steps)
            currentDestination = BottomNavigationDestination.Steps
         },
         icon = {
            Icon(painter = painterResource(R.drawable.steps_icon), contentDescription = null)
         },
         label = { Text(text = TextConstants.STEPS) }
      )
   }
}
