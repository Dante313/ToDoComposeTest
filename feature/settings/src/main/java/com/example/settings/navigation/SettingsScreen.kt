package com.example.settings.navigation


import androidx.compose.material.*
import androidx.compose.runtime.Composable


@Composable
fun SettingsRoute() {
    SettingsScreen()
}

@Composable
fun SettingsScreen() {
    Surface {
        Text(text = "Settings!")
    }
}