package com.nsdevelopment.mobile.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsdevelopment.mobile.components.Chip
import com.nsdevelopment.mobile.components.Label
import com.nsdevelopment.mobile.components.slidingButton.SlidingButton

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    actions: (HomeScreenActions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column() {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Label(
                    text = "Meal Categories",
                    style = MaterialTheme.typography.titleLarge
                )
                Label(
                    text = "See All",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier.clickable(onClick = { actions.invoke(HomeScreenActions.ResetMealCategoriesFilter) })
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier.horizontalScroll(
                    state = rememberScrollState()
                )
            ) {
                state.mealsCategories.forEach { item ->
                    Chip(
                        selected = item.value,
                        label = item.key,
                        onClick = {
                            actions.invoke(HomeScreenActions.ToggleMealCategoriesFilter(item.key))
                        }
                    )
                }
            }
        }

        Column {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Label(
                    text = "Cuisine",
                    style = MaterialTheme.typography.titleLarge
                )

                Label(
                    text = "See All",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = modifier.clickable(onClick = { actions.invoke((HomeScreenActions.ResetCuisineFilters)) })
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier.horizontalScroll(
                    state = rememberScrollState()
                )
            ) {
                state.cuisines.forEach { item ->
                    Chip(
                        selected = item.value,
                        label = item.key,
                        onClick = { actions.invoke(HomeScreenActions.ToggleCuisineFilter(item.key)) }
                    )
                }
            }
        }

        SlidingButton(
            leftSide = "All",
            rightSide = "Favourites",
            onClick = {}
        )
    }
}
