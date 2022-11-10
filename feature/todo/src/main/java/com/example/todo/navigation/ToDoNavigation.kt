package com.example.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.todo.ToDoRoute

const val toDoNavigationRoute = "todo_route"

fun NavController.navigateToDo(navOptions: NavOptions? = null) {
    this.navigate(toDoNavigationRoute, navOptions)
}

fun NavGraphBuilder.toDoScreen() {
    composable(route = toDoNavigationRoute) {
        ToDoRoute()
    }
}