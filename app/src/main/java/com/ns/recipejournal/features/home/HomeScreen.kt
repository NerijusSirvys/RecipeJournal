package com.ns.recipejournal.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ns.recipejournal.features.home.components.ChipFilter
import com.ns.recipejournal.features.home.components.RecipeCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val vm = koinViewModel<HomeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        onAction = vm::onAction,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    onAction: (HomeScreenAction) -> Unit
) {
    Column {
        ChipFilter(
            name = "Meal Category",
            options = state.categories,
            onClick = {}
        )
        Spacer(Modifier.height(10.dp))
        ChipFilter(
            name = "Cuisines",
            options = state.cuisines,
            onClick = {}
        )
        Spacer(Modifier.height(25.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.recipes, key = { it.id }) {
                RecipeCard(
                    details = it,
                    onToggleFavourite = { onAction(HomeScreenAction.ToggleFavourite(it.id)) }
                )
            }
        }
    }
}