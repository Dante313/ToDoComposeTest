package com.example.todocompose.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.todocompose.R

enum class BottomBarDestination(
    val icon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int
) {
    TO_DO(
        icon = Icons.Default.List,
        iconTextId = R.string.todo,
        titleTextId = R.string.todo
    ),
    SETTINGS(
        icon = Icons.Default.Settings,
        iconTextId = R.string.settings,
        titleTextId = R.string.settings
    )
}