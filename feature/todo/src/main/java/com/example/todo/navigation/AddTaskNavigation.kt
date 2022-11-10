package com.example.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.todo.ToDoRoute

const val addTaskNavigation = "add_task"

fun NavController.navigateToAddTask(navOptions: NavOptions? = null) {
    this.navigate(addTaskNavigation, navOptions)
}

fun NavGraphBuilder.addTaskScreen() {
    composable(route = addTaskNavigation) {
//        ToDoRoute(navController)
    }
}