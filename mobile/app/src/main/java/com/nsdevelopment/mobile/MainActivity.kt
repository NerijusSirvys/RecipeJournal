package com.nsdevelopment.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nsdevelopment.mobile.components.drawer.NavDrawer
import com.nsdevelopment.mobile.ui.theme.MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        enableEdgeToEdge()
        setContent {
            MobileTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                NavDrawer(
                    drawerState = drawerState,
                    onClick = { item -> println(item) }
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = "Hello Jimmy")
                        }
                    }
                }
            }
        }
    }
}