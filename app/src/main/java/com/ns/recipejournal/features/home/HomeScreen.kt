package com.ns.recipejournal.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val vm = koinViewModel<HomeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            HomeScreenContent(
                modifier = Modifier.align(alignment = Alignment.TopCenter),
                state = state,
                onAction = vm::onAction,
            )
        }
    }
}
