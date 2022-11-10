package com.example.coreui.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.ToDoNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        selectedContentColor = ToDoNavigationDefaults.navigationSelectedItemColor(),
        unselectedContentColor = ToDoNavigationDefaults.navigationContentColor(),
    )
}

@Composable
fun ToDoNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        content = content,
        contentColor = ToDoNavigationDefaults.navigationContentColor(),
        elevation = 0.dp,
    )
}

object ToDoNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colors.onSurface
    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colors.onPrimary
}