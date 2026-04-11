package com.nsdevelopment.mobile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nsdevelopment.mobile.ui.theme.ApplicationTheme
import com.nsdevelopment.mobile.utilities.SystemPreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit,
    title: String,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        }
    )
}

@SystemPreviews
@Composable
private fun Preview() {
    ApplicationTheme {
        Scaffold(
            topBar = {
                ScreenTopBar(
                    title = "Test Screen",
                    onMenuClick = {}
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding))
        }

    }
}