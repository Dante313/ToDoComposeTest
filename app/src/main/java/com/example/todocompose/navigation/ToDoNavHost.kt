package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.settings.navigation.settingsScreen
import com.example.todo.navigation.addTaskScreen
import com.example.todo.navigation.toDoNavigationRoute
import com.example.todo.navigation.toDoScreen

@Composable
fun ToDoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = toDoNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        toDoScreen(navController)
        addTaskScreen()
        settingsScreen()
    }
}