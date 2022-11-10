package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todo.navigation.toDoNavigationRoute
import com.example.todo.navigation.toDoScreen

@Composable
fun ToDoNavHost(
    navController: NavHostController,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = toDoNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        toDoScreen()
//        settingsScreen()
    }
}