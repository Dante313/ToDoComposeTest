package com.example.todocompose.navigation

import androidx.annotation.StringRes
import com.example.coreui.ui.theme.ToDoIcons
import com.example.todocompose.R
import com.example.coreui.ui.theme.Icon.DrawableResourceIcon
import com.example.coreui.ui.theme.Icon

enum class BottomBarDestination(
    val icon: Icon,
    @StringRes val iconTextId: Int
) {
    TO_DO(
        icon = DrawableResourceIcon(ToDoIcons.Task),
        iconTextId = R.string.todo
    ),
    SETTINGS(
        icon = DrawableResourceIcon(ToDoIcons.Settings),
        iconTextId = R.string.settings
    )
}