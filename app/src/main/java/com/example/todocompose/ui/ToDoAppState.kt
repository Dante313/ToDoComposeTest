package com.example.todocompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.settings.navigation.navigateToSettings
import com.example.settings.navigation.settingsNavigationRoute
import com.example.todo.navigation.navigateToDo
import com.example.todo.navigation.toDoNavigationRoute
import com.example.todocompose.navigation.BottomBarDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberToDoAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) = remember(navController, coroutineScope) {
    ToDoAppState(navController, coroutineScope)
}

@Stable
class ToDoAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() =
            navController.currentBackStackEntryAsState().value?.destination

    val currentBottomBarDestination: BottomBarDestination?
        @Composable get() = when (currentDestination?.route) {
            toDoNavigationRoute -> BottomBarDestination.TO_DO
            settingsNavigationRoute -> BottomBarDestination.SETTINGS
            else -> null
        }

    val bottomBarDestinations: List<BottomBarDestination> = BottomBarDestination.values().asList()

    fun navigateToBottomBarDestination(bottomBarDestination: BottomBarDestination) {
        val bottomBarNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (bottomBarDestination) {
            BottomBarDestination.TO_DO -> navController.navigateToDo(bottomBarNavOptions)
            BottomBarDestination.SETTINGS -> navController.navigateToSettings(bottomBarNavOptions)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}