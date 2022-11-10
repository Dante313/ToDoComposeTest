package com.example.todocompose.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.coreui.ui.navigation.ToDoNavigationBar
import com.example.coreui.ui.navigation.ToDoNavigationBarItem
import com.example.todocompose.navigation.BottomBarDestination
import com.example.todocompose.navigation.ToDoNavHost

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ToDoApp(
    appState: ToDoAppState = rememberToDoAppState()
) {
    Scaffold(
        contentColor = MaterialTheme.colors.onBackground,
        bottomBar = {
            ToDoBottomBar(
                destinations = appState.bottomBarDestinations,
                onNavigateToDestination = appState::navigateToBottomBarDestination,
                currentDestination = appState.currentDestination
            )
        },
    ) { padding ->
        ToDoNavHost(
            navController = appState.navController,
            onBack = appState::onBackClick,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }
}

@Composable
fun ToDoBottomBar(
    destinations: List<BottomBarDestination>,
    onNavigateToDestination: (BottomBarDestination) -> Unit,
    currentDestination: NavDestination?
) {
    ToDoNavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            ToDoNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { destination.icon },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomBarDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false