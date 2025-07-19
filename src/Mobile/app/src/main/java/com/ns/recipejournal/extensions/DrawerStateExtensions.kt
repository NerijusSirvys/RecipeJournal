package com.ns.recipejournal.extensions

import androidx.compose.material3.DrawerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun DrawerState.CloseInScope(scope: CoroutineScope) {
   if (this.isOpen) {
      scope.launch {
         this@CloseInScope.close()
      }
   }
}