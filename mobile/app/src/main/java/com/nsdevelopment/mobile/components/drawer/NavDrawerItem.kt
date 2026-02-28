package com.nsdevelopment.mobile.components.drawer

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun NavDrawerItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    @DrawableRes iconId: Int
) {
    NavigationDrawerItem(
        modifier = modifier,
        label = { Text(text = label) },
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null
            )
        },
    )
}